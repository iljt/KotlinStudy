package com.example.coroutines.paging3.di

import android.app.Application
import androidx.room.Room
import com.example.coroutines.paging3.db.AppDatabase
import com.example.coroutines.paging3.db.RssRecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**

 * Created  by Administrator on 2022/1/23 19:12

 */
@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun providerAppDataBase(application: Application): AppDatabase {
        return Room.databaseBuilder(application,AppDatabase::class.java,"rss_record_db").build()
    }

    @Singleton
    @Provides
    fun providerRssRecordDao(database: AppDatabase): RssRecordDao {
        return database.rssDao()
    }
}