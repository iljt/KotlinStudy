package com.example.coroutines.paging3.init

import android.content.Context
import androidx.startup.Initializer

/**

 * Created  by Administrator on 2022/1/25 00:08

 */
class AppInitializer :Initializer<Unit> {
    override fun create(context: Context) {
        AppHelper.init(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
       return mutableListOf()
    }
}