package com.example.coroutines.paging3.di

import com.example.coroutines.paging3.api.RssApi
import com.example.coroutines.paging3.db.AppDatabase
import com.example.coroutines.paging3.repository.Repository
import com.example.coroutines.paging3.repository.RssRecordRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton

/**

 * Created  by Administrator on 2022/1/23 22:13

 */

@InstallIn(ActivityComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun providerRssRecordRepository(
        api: RssApi,
        database: AppDatabase
    ): Repository{
        return RssRecordRepositoryImpl(api, database)
    }
}