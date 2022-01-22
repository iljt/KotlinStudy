package com.example.coroutines.paging3.paging

import androidx.paging.PagingSource
import com.example.coroutines.paging3.api.RssApi
import com.example.coroutines.paging3.entity.Record
import com.example.coroutines.paging3.entity.RequestRssDataBean
import com.example.coroutines.paging3.net.RetrofitClient

/**

 * Created  by Administrator on 2022/1/22 23:15

 */
class RssPagingSource: PagingSource<Int, Record>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Record> {
        val size=params.loadSize
        var current=params.key ?: 1
        val requestRssDataBean=RequestRssDataBean(size,current)
        val rssData= RetrofitClient.createApi(RssApi::class.java).queryRssList(requestRssDataBean)
        /*if (rssData.isSuccess()){

        }*/

        var prevkey:Int? = null
        var nextkey:Int? = null

        prevkey = if(current ==1) null else current - 1
        nextkey = if ( rssData?.data?.total > rssData?.data?.current*rssData?.data?.size) current+1 else null

        return  try{
            LoadResult.Page(
                data =rssData?.data?.records,
                prevKey = prevkey,
                nextKey=nextkey
            )
        }catch (e:Exception){
            e.printStackTrace()
            return LoadResult.Error(e)
        }


    }
}