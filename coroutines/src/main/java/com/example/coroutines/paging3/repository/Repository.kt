package com.example.coroutines.paging3.repository

import androidx.paging.PagingData
import com.example.coroutines.paging3.entity.Record
import kotlinx.coroutines.flow.Flow

/**

 * Created  by Administrator on 2022/1/23 21:41

 */
interface  Repository {
    fun fetchRssRecordList():Flow<PagingData<Record>>
}