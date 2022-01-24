package com.example.coroutines.paging3.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.coroutines.paging3.api.RssApi
import com.example.coroutines.paging3.db.AppDatabase
import com.example.coroutines.paging3.entity.Record
import com.example.coroutines.paging3.remote.RssMediator
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

/**

 * Created  by Administrator on 2022/1/23 21:43
    PagingSource：负责提供源数据，一般是网络请求或者数据库查询，
    PagingData：分页数据的容器，负责一些分页的参数设定和订阅源数据流
    PagingDataAdapter：跟常规的RecyclerivewAdapter一样把数据转换成UI
    LoadStateAdapter：可以添加页头/页脚，方便实现loadmore的样式
    https://blog.csdn.net/weixin_42046829/article/details/110140424
 */
class RssRecordRepositoryImpl(
    private val api: RssApi,
    private  val database: AppDatabase):Repository{

    override fun fetchRssRecordList(): Flow<PagingData<Record>> {
       return Pager(
           config = PagingConfig(
               // 每页显示的数据的大小。对应 PagingSource 里 LoadParams.loadSize
               pageSize =10,
               // 预刷新的距离，距离最后一个 item 多远时加载数据，默认为 pageSize
               prefetchDistance = 3,
               // 初始化加载数量，默认为 pageSize * 3
               initialLoadSize = 20,
               ) ,
         remoteMediator = RssMediator(api,database)
       ){
           database.rssDao().getRecord()
       }.flow.flowOn(IO).map { pagingData ->
           pagingData.map {
               it
           }
       }

    }

}