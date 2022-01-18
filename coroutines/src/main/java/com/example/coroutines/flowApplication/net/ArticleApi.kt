package com.example.coroutines.flowApplication.net

import com.example.coroutines.flowApplication.entity.Article
import retrofit2.http.GET
import retrofit2.http.Query

/**

 * Created  by Administrator on 2022/1/18 23:57

 */
interface ArticleApi {

    @GET("article")
    suspend fun searchArticle(@Query("key") key: String):List<Article>
}