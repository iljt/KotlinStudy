package com.example.coroutines.flowApplication.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**

 * Created  by Administrator on 2022/1/18 21:38

 */
@Entity
data class User(
    @PrimaryKey val userId:Int,
    @ColumnInfo(name="firstName") val firstName:String,
    @ColumnInfo(name="lastName") val lastName:String
)