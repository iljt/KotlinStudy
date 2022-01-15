package com.example.kotlinstudy.basic.period3

// TODO 42.Kotlin语言的先决条件函数
fun main() {
    var value1: String ? = null
    var value2: Boolean = true
    //判断值是否为null否则报错 java.lang.IllegalStateException: Required value was null.
    // checkNotNull(value1)

   // requireNotNull(value1) // java.lang.IllegalArgumentException: Required value was null.
   //判断值是否为true否则报错java.lang.IllegalArgumentException: Failed requirement.
    require(value2)
}