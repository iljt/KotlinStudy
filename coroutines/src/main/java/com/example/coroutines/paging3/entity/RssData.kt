package com.example.coroutines.paging3.entity

import com.google.gson.annotations.SerializedName

/**

 * Created  by Administrator on 2022/1/22 22:53

 */
/*data class RssData(
    val `data`: Data
)*/

data class RssData(
    @SerializedName("current")
    val current: Int,
    @SerializedName("records")
    val records: List<Record>,
    @SerializedName("size")
    val size: Int,
    @SerializedName("total")
    val total: Int
)

data class Record(
    val background: String? ="",
    val icon: String? ="",
    val isSubscribe: Int,
    val rssBrief: String? ="",
    val rssId: Int,
    val rssName: String? =""
)