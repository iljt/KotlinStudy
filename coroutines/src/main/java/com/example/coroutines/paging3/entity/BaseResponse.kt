package com.example.coroutines.paging3.entity

/**

 * Created  by Administrator on 2022/1/22 22:59

 */
class BaseResponse<T>(val msg:String,val code:Int ,val data: T) {

    //请求成功
    fun isSuccess(): Boolean{
       return code==0
    }
}