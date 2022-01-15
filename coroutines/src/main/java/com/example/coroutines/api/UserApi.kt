package com.example.coroutines.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**

 * Created  by Administrator on 2022/1/9 11:18

 */
data class User(var name:String="", var address:String="")

val  userServiceApi: UserServiceApi by lazy {
    val retrofit= Retrofit.Builder()
        .client(OkHttpClient().newBuilder().addInterceptor {
            it.proceed(it.request()).apply {
                Log.e("okhttp: ","request:${code()}")
            }
        }.build())
        .baseUrl("http://192.168.1.4/kotlinstudyserver/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
    retrofit.create(UserServiceApi::class.java)
}

interface UserServiceApi{

    @GET("user")
    fun loadUser(@Query("name") name: String): Call<User>


    @GET("user")
    suspend fun getUser(@Query("name") name: String): User
}