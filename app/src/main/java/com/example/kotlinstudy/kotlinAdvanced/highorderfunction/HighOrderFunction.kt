package com.example.kotlinstudy.kotlinAdvanced.highorderfunction

/**

 * Created  by Administrator on 2021/12/12 23:40
   函数返回一个函数
 */
var fun1=fun(aaa:Int, bbb:Int):(Int, String)->String={ num, str->
       "相加的结果是:${num.toString() + str}  $aaa"
        num.toString() + str
}

fun main(){
    println(fun1(100,200) )
    //fun函数的2个参数数aaa，bbb是没有用到的
    println(fun1(100,200)(500,"400") )//500400
}
