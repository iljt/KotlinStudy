package com.example.coroutines.flowApplication.net

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**

 * Created  by Administrator on 2022/1/18 23:55

 */
object RetrofitClient {

   private val instance:Retrofit by lazy {
      val interceptor = HttpLoggingInterceptor{
          Log.e("okhttp: ",it)
      }
       interceptor.level=HttpLoggingInterceptor.Level.BODY
       Retrofit.Builder()
           .client(OkHttpClient().newBuilder().addInterceptor (interceptor)
           .build())
           .baseUrl("http://192.168.1.4/kotlinstudyserver/")
           .addConverterFactory(MoshiConverterFactory.create())
           .build()
   }

    fun <T> createApi(clazz:Class<T>) :T{
       return instance.create(clazz) as T
    }

    val articleApi:ArticleApi by lazy{
        instance.create(ArticleApi::class.java)
    }
}