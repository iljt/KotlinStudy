package com.example.kotlinstudy.kotlinAdvanced.coroutines.use.api

import android.text.TextUtils
import android.util.Log
import com.example.kotlinstudy.BuildConfig
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okio.Buffer
import okio.EOFException
import okio.GzipSource
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

/**
 * 访问服务器的API接口(WanAndroidAPI) 的 客户端管理
 */
class APIClient {

    private object Holder {  // 单例
        val INSTANCE = APIClient()
    }
    companion object { // 派生
        val instance = Holder.INSTANCE
    }

    // WanAndroidAPI实例化这个，  XXXAPI实例化这个，   BBBAPI实例化
    fun <T> instanceRetrofit(apiInterface: Class<T>) : T {

        // OKHttpClient请求服务器
        val mOkHttpClient = OkHttpClient().newBuilder().myApply {
            readTimeout(10000, TimeUnit.SECONDS) // 添加读取超时时间
            connectTimeout(10000, TimeUnit.SECONDS) // 添加连接超时时间
            writeTimeout(10000, TimeUnit.SECONDS) // 添加写出超时时间
           /* addInterceptor(object :Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    TODO("Not yet implemented")
                }

            })*/
            addInterceptor(HttpLogInterceptor(BuildConfig.DEBUG))
        }.build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")

        // 请求方  ←
        .client(mOkHttpClient)
        // 响应方  →
        // Response的事情  回来
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJava来处理
        .addConverterFactory(GsonConverterFactory.create()) // Gson 来解析 --- JavaBean
        .build()

        return retrofit.create(apiInterface);
    }
}

// 默认 无参数的
fun <T> T.myApply(mm: T.() -> Unit) : T {
    // T == this
    mm()
    return this
}


class HttpLogInterceptor(val logEnable: Boolean): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        // chain中拿要拦截的信息
        if (!logEnable) return chain.proceed(chain.request())

        // request
        val request = chain.request()
        val method = request.method
        val url = request.url
        Log.e("HttpLog.eng", "【Http】Request start")
        Log.e("HttpLog.eng", "【Http】$method $url")

        // connection
        val connection = chain.connection()
        val protocol = connection?.protocol()
        protocol?.let { Log.e("HttpLog.eng", "【Http】protocol:$it") }

        // headers
        val requestHeaders = request.headers
        requestHeaders.forEach {
            Log.e("HttpLog.eng", "【Http】[header]${it.first}:${it.second}")
        }

        // body
        val reqBody = request.body
        reqBody?.contentType()?.let { Log.e("HttpLog.eng", "【Http】Content-Type:${it}") }
        reqBody?.contentLength()?.let { Log.e("HttpLog.eng", "【Http】Content-Length:${it}") }
        Log.e("HttpLog.eng", "【Http】Request $method end")




        Log.e("HttpLog.eng", "【Http】Response $method start")
        val startNs = System.nanoTime()
        // response
        val response: Response
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            Log.e("HttpLog.eng", "【Http】Failed, e:${e.message}")
            throw e
        }
        val tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs)
        Log.e("HttpLog.eng", "【Http】code:${response.code} ($tookMs ms)")

        // headers
        val respHeaders = response.headers
        respHeaders.forEach {
            Log.e("HttpLog.eng", "【Http】[header]${it.first}:${it.second}")
        }

        // body
        val respBody = response.body
        response.message.let {
            if (!TextUtils.isEmpty(it)) Log.e("HttpLog.eng", "【Http】message:${it}")
        }

        val contentLength = respBody?.contentLength()
        val bodySize = if (contentLength != -1L) "$contentLength-byte" else "unknown-length"

        if (a(respHeaders)) {
            Log.e("HttpLog.eng", "【Http】Response $method end ($bodySize)")
        } else {
            val source = respBody?.source()
            source?.request(9223372036854775807L)
            var buffer = source?.buffer
            var gzippedLength = 0L
            if ("gzip".equals(respHeaders["Content-Encoding"], true)) {
                gzippedLength = buffer?.size!!
                var gzippedResponseBody: GzipSource? = null
                try {
                    gzippedResponseBody = GzipSource(buffer.clone())
                    buffer = Buffer()
                    buffer.writeAll(gzippedResponseBody)
                } finally {
                    gzippedResponseBody?.close()
                }
            }
            val mediaType = respBody?.contentType()
            val charset = mediaType?.charset(Charset.forName("UTF-8"))

            if (buffer != null && !a(buffer)) {
                Log.e("HttpLog.eng", "【Http】Response $method end")
                return response
            }

            if (contentLength != 0L) {
                charset?.let {
                    Log.e("HttpLog.eng",
                        "【Http】msg:${buffer?.clone()?.readString(it)}")
                }
            }

            Log.e("HttpLog.eng", "【Http】Response $method end (${buffer?.size}-byte"
                    + if (gzippedLength != 0L) ", $gzippedLength--gzipped-byte)" else ")")
        }
        return response
    }

    private fun a(headers: Headers): Boolean {
        val contentEncoding = headers["Content-Encoding"]
        return contentEncoding != null && !contentEncoding.equals("identity",
            true) && !contentEncoding.equals("gzip", true)
    }

    private fun a(buffer: Buffer): Boolean {
        return try {
            val prefix = Buffer()
            val byteCount = if (buffer.size < 64L) buffer.size else 64L
            buffer.copyTo(prefix, 0L, byteCount)
            var i = 0
            while (i < 16 && !prefix.exhausted()) {
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
                ++i
            }
            true
        } catch (var6: EOFException) {
            false
        }
    }
}