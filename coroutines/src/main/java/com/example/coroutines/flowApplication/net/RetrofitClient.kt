package com.example.coroutines.flowApplication.net

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**

 * Created  by Administrator on 2022/1/18 23:55

 */
object RetrofitClient {

   private val instance:Retrofit by lazy {
       Retrofit.Builder()
           .client(OkHttpClient().newBuilder().addInterceptor {
               it.proceed(it.request()).apply {
                   Log.e("okhttp: ","request:${code()}")
               }
           }.build())
           .baseUrl("http://192.168.1.4/kotlinstudyserver/")
           .addConverterFactory(MoshiConverterFactory.create())
           .build()
   }

    val articleApi:ArticleApi by lazy{
        instance.create(ArticleApi::class.java)
    }
}