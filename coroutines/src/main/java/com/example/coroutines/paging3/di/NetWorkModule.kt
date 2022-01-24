package com.example.coroutines.paging3.di

import android.util.Log
import com.example.coroutines.paging3.api.RssApi
import com.example.coroutines.paging3.constant.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**

 * Created  by Administrator on 2022/1/23 21:13

 */
@InstallIn(ApplicationComponent::class)
@Module
class NetWorkModule {

    @Singleton
    @Provides
    fun providerOkhttpClient(okHttpClient: OkHttpClient):OkHttpClient{
        return OkHttpClient.Builder().build()

    }

    @Singleton
    @Provides
    fun providerRetrofit(okHttpClient: OkHttpClient):Retrofit{
        val interceptor = HttpLoggingInterceptor{
            Log.e("okhttp: ",it)
        }
        interceptor.level= HttpLoggingInterceptor.Level.BODY
        return Retrofit.Builder()
            .client(OkHttpClient().newBuilder().addInterceptor (interceptor)
                .build())
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providerRssApi(retrofit: Retrofit): RssApi {
        return retrofit.create(RssApi::class.java)
    }


}