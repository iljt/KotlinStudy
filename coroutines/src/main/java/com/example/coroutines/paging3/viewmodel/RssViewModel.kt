package com.example.coroutines.paging3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.coroutines.paging3.entity.Record
import com.example.coroutines.paging3.paging.RssPagingSource
import kotlinx.coroutines.flow.Flow

/**

 * Created  by Administrator on 2022/1/22 23:28

 */
class RssViewModel:ViewModel() {

    fun queryRssList(): Flow<PagingData<Record>> {
       return Pager(
            config = PagingConfig(
                // 每页显示的数据的大小。对应 PagingSource 里 LoadParams.loadSize
                pageSize =10,
                // 预刷新的距离，距离最后一个 item 多远时加载数据，默认为 pageSize
                prefetchDistance = 3,
                // 初始化加载数量，默认为 pageSize * 3
                initialLoadSize = 30,

            ) ,
            pagingSourceFactory = {RssPagingSource()}
        ).flow
    }
}