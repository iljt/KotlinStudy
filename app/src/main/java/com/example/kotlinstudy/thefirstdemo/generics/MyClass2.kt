package com.example.kotlinstudy.thefirstdemo.generics

/**

 * Created  by Administrator on 2021/6/6 12:08
  泛型方法
 */
class MyClass2 {
    //泛型方法 M与类上面的T没有关系
    fun <M> method2(param: M):M{
        return param
    }
    //泛型上界 M只能指定为数字类型
    fun <M:Number> method3(param: M):M{
        return param
    }

    //泛型仿apply函数
    fun <P> P.build(block:P.()->Unit):P{
        block()
        //this代表P对象
        return this
    }
}