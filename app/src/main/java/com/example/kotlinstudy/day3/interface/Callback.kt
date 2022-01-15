package com.example.kotlinstudy.day3.`interface`

import java.io.IOException

interface Callback {

    fun onError(e:IOException)
    fun onSuccess(resultJson:String)/*{
      // 接口方法可以有默认实现
        Log.e("Callback","onSuccess")
         }*/
}