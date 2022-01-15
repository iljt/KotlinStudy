package com.example.kotlinstudy.basic.period2

// TODO 24.Kotlin语言的函数类型&隐式返回学习
fun main() {
    // 我们现在在写函数

    // 第一步：函数输入输出的声明
    val methodAction : () -> String

    // 第二步：对上面函数的实现
    methodAction = {
        val inputValue = 999999
        "$inputValue xxx" // == 背后隐式 return "$inputValue xxx";
        // 匿名函数不要写return，最后一行就是返回值
    }

    // 第三步：调用此函数
    println(methodAction())
}

/*
fun methodAction() : String {
    return "xxx"
}
 */