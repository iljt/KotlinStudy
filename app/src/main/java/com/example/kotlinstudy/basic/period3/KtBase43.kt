package com.example.kotlinstudy.basic.period3

const val INFO = "abc def"

// TODO 43.Kotlin语言的substring
fun main() {
    val indexOf = INFO.indexOf('d')
    println(INFO.substring(0, indexOf))
    println(INFO.substring(0 until indexOf)) // KT基本上用此方式： 0 until indexOf
}