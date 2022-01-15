package com.example.kotlinstudy.basic.period4

// TODO 63.Kotlin语言的可变Set集合
fun main() {
   val set : MutableSet<String> = mutableSetOf("xxx", "zzz")
    set += "yyy"
    set += "ttt"
    set -= "zzz"
    set.add("111")
    set.remove("ttt")
    println(set)
}