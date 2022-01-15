package com.example.kotlinstudy.basic.period2

// TODO 23.Kotlin语言的匿名函数学习
fun main() {
    val len = "xxxy".count()
    println(len)

    val len2 = "xxxyy".count {
        // it 等价于 D e r r y 的字符 Char
        it == 'y'
    }
    println(len2)
}

