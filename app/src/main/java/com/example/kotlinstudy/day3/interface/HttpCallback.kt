package com.example.kotlinstudy.day3.`interface`


abstract class HttpCallback:Callback {

    override fun onSuccess(jsonResult: String){
        // Log.e("HttpCallback-onSuccess",resultJson)
        // 写一点伪代码 获取类上的泛型，gson 去转成可以直接使用的对象
        onSuccess()
    }

    abstract  fun onSuccess();

}
