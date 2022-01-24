package com.example.coroutines.paging3.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coroutines.paging3.entity.Record

/**

 * Created  by Administrator on 2022/1/23 18:57

 */

@Dao
interface RssRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertRecordList(recordlist:List<Record>)

    @Query("DELETE FROM Record")
     fun clearRecordlist()

    @Query("SELECT *  FROM Record")
    fun getRecord():PagingSource<Int,Record>
}