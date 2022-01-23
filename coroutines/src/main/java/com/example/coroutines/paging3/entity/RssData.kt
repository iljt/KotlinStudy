package com.example.coroutines.paging3.entity
/**

 * Created  by Administrator on 2022/1/22 22:53

 */
/*data class RssData(
    val `data`: Data
)*/

data class RssData(
    val current: Int,
    val records: List<Record>,
    val size: Int,
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