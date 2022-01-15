package com.example.kotlinstudy.kotlinAdvanced.diyinnerfun

/**

 * Created  by Administrator on 2021/12/18 17:13

 */

fun main() {
    repeat(3){
        println("下标是:$it ")
    }

    println("-------------")

    myRepeat(6){
        println("index是:$it ")
    }
    println("--------------------------------")
    10.myRepeat2 {
        print("下标是:$it ")
    }
}

private inline fun myRepeat(repeatTimes:Int,actions:(Int)->Unit)= run {  for (item in 0 until repeatTimes) actions(item)}

private inline fun Int.myRepeat2(actions:(Int)->Unit)= run {  for (item in 0 until this) actions(item)}