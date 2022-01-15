package com.example.kotlinstudy.kotlinAdvanced.diyinnerfun

/**

 * Created  by Administrator on 2021/12/18 17:29

 */
fun main() {
    // true 返回 xxx，  false会返回一个null
    var x="xxx".takeIf {
        "sdf"
        1
        true
        false
    }
    println(x)
    println("---------------------------")
    var z="yyyy".myTakeIf {
        "sdf"
        1
        true
        true
    }
    println(z)
}

private inline fun <T> T.myTakeIf(action:(T)->Boolean)=if(action(this)) this else null
