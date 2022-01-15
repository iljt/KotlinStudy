package com.example.kotlinstudy.basic.period6

import com.example.kotlinstudy.basic.period6.ext.randomItemValue as aa  // as g 重命名扩展操作
import com.example.kotlinstudy.basic.period6.ext.randomItemValuePrintln as bb // as g 重命名扩展操作

// TODO 121-Kotlin语言的重命名扩展学习
fun main() {

    val list : List<String> = listOf("zhangsan", "lisi", "wangwu")
    val set : Set<Double> = setOf(545.5, 434.5, 656.6)

    // 使用 扩展文件
    println(list.aa())
    println(set.aa())

    println()

    list.bb()
    set.bb()
}