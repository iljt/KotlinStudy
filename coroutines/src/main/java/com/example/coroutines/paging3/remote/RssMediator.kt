package com.example.coroutines.paging3.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.coroutines.paging3.api.RssApi
import com.example.coroutines.paging3.db.AppDatabase
import com.example.coroutines.paging3.entity.BaseResponse
import com.example.coroutines.paging3.entity.Record
import com.example.coroutines.paging3.entity.RequestRssDataBean
import com.example.coroutines.paging3.entity.RssData
import com.example.coroutines.paging3.net.RetrofitClient
import java.lang.Exception

/**

 * Created  by Administrator on 2022/1/23 21:34

 */
@OptIn(ExperimentalPagingApi::class)
class RssMediator(
    private val api: RssApi,
    private  val database: AppDatabase)
    : RemoteMediator<Int, Record>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Record>): MediatorResult {
        try {
            //1、判断loadType
            Log.e("loadType= ","" + loadType)
            var pageCount = Int.MAX_VALUE
            var curPage = 0
            var nextkey:Int? =null
            var rssData:BaseResponse<RssData>? =null
            val pageKey = when(loadType){
                //首次访问或者调用PagingDataAdapter.refresh()
                LoadType.REFRESH -> null
                //在当前加载的数据集开头加载数据时
                LoadType.PREPEND -> return  MediatorResult.Success(endOfPaginationReached = true)
                //加载更多时
                LoadType.APPEND -> {
                   // val lastItem:Record = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = false)
                  //  lastItem.page
                    if ( rssData?.data?.total!! > rssData?.data?.current*rssData?.data?.size) curPage+1 else  return MediatorResult.Success(endOfPaginationReached = false)
                }
            }
            //2、请求网络分页数据
            val page = pageKey ?: 1
            val requestRssDataBean= RequestRssDataBean(pageCount,page)
             rssData= api.queryRssList(requestRssDataBean)
            curPage = rssData?.data?.current
            pageCount = rssData?.data?.size
            val data = rssData?.data.records
            val endOfPaginationReached = data.isEmpty()
            //3、插入数据库
            val rssDao = database.rssDao()
            database.withTransaction {
                if(loadType == LoadType.REFRESH){
                    rssDao.clearRecordlist()
                }
                rssDao.insertRecordList(data)
            }

            return  MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        }catch (e:Exception){
            e.printStackTrace()
            return  MediatorResult.Error(e)
        }
    }

}