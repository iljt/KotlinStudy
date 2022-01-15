package com.example.kotlinstudy.kotlinAdvanced.diyinnerfun

/**

 * Created  by Administrator on 2021/12/15 23:16

 */
fun main(){
    var names= listOf("aa","bb","cc")
    var ages= listOf(11,22,33)
    names.zip(ages).toMap().map {
        " you name:${it.key}, you age:${it.value}"
    }.map {
        print(it)
    }


}
