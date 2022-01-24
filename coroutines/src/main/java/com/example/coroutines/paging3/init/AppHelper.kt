package com.example.coroutines.paging3.init

import android.annotation.SuppressLint
import android.content.Context

/**

 * Created  by Administrator on 2022/1/25 00:07

 */
@SuppressLint("StaticFieldLeak")
object AppHelper {

    lateinit var mContext:Context

    fun init(context: Context){
        this.mContext=context
    }
}