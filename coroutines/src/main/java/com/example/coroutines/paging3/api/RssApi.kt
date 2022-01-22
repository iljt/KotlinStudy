package com.example.coroutines.paging3.api

import com.example.coroutines.paging3.entity.*
import retrofit2.http.Body
import retrofit2.http.POST

/**

 * Created  by Administrator on 2022/1/22 23:05

 */
interface RssApi {

    //查询所有订阅源
    @POST("/api/advisory/v1/userApp/queryRssList")
    suspend fun queryRssList(@Body requestRssDataBean:RequestRssDataBean):BaseResponse<RssData>
}