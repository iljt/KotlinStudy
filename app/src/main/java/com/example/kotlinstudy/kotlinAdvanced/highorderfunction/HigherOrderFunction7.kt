package com.example.kotlinstudy.kotlinAdvanced.highorderfunction

import java.util.function.Function

/**

 * Created  by Administrator on 2021/12/13 00:06

 */


fun main(){
    // 以前的做法(匿名函数体)
    show {
        "我的值是:$it"
    }
    // 一般源码的做法
    // :: 把这个函数变成函数引用，就可以传递赋值给变量了
    show(::lambdaImpl)
    val r1:Function1<String,String> = ::lambdaImpl
    val r2:(String)->String=r1
    val r3:String.()->String=r2 // String.() == (String)   String.() Int.属于来源，会自动把来源作为第一个参数
    // Int.(String) -> Unit表示方法是两个参数的？  == (Int, String) -> Unit
    show(r3)
}


fun lambdaImpl(str:String)= "值是:$str"

fun show(action:(String)->String){
    println(action("sss"))
}