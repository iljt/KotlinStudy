package com.example.coroutines.paging3.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

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

@Entity
data class Record(
    val background: String? ="",
    val icon: String? ="",
    val isSubscribe: Int,
    val rssBrief: String? ="",
    @PrimaryKey
    val rssId: Int,
    val rssName: String? =""
)