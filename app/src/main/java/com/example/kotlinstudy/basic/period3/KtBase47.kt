package com.example.kotlinstudy.basic.period3

// TODO 47.Kotlin语言的字符串遍历操作
// "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
fun main() {
    val str = "ABCDEFG"
    str.forEach {  c -> // 覆盖默认的it参数名，修改参数名为 c
        // it == str的每一个字符 A B C D ...
        // print("所有的字符是:$it  ")
        println("所有的字符是:$c  ")
    }
    println()
    str.myForEach{
        println("所有的字符是:$it  ")
    }
}

inline fun CharSequence.myForEach(action:(Char)->Unit): Unit= run { for (element in this) action(element) }