package com.example.coroutines.paging3.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coroutines.paging3.entity.Record

/**

 * Created  by Administrator on 2022/1/23 18:56

 */
@Database(entities = [Record::class], version = 1, exportSchema = false)
abstract  class AppDatabase : RoomDatabase(){

    abstract  fun  rssDao(): RssRecordDao
}