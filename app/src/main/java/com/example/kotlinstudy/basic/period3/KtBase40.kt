package com.example.kotlinstudy.basic.period3

// TODO 40.在Kotlin空合并操作符
fun main() {
    var name: String? = "xxx"
     //name = null

    // 空合并操作符  xxx ?: "原来你是null啊"
    // "如果xxx等于null，就会执行 ?: 后面的区域"
     name ?:"name是null"


    // let函数 + 空合并操作符
    println(name?.let { "name=$it" } ?: "name是null")
}