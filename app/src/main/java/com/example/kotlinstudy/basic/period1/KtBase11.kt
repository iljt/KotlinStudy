package com.example.kotlinstudy.basic.period1

// TODO 17.Kotlin中函数参数的默认参数
fun main() {
    action01("lisi", 89)
    action02("wangwu")
    action03()

    action03("赵六", 76)
}

private fun action01(name: String, age: Int) {
    println("我的姓名是:$name, 我的年龄是:$age")
}

private fun action02(name: String, age: Int = 77) {
    println("我的姓名是:$name, 我的年龄是:$age")
}

private fun action03(name: String = "王五", age: Int = 67) {
    println("我的姓名是:$name, 我的年龄是:$age")
}