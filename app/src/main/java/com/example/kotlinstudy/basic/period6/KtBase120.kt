package com.example.kotlinstudy.basic.period6

// 导入扩展文件
// 在工作中非常有用，可以把很多的扩展操作，写到某一个地方，到时候引入过来用，比较独立化
import com.example.kotlinstudy.basic.period6.ext.randomItemValue
import com.example.kotlinstudy.basic.period6.ext.randomItemValuePrintln

// TODO 120-Kotlin语言的定义扩展文件
fun main() {
    val list : List<String> = listOf("zhangsan", "lisi", "wangwu")
    val set : Set<Double> = setOf(545.5, 434.5, 656.6)

    // 如果不使用 扩展文件
    println(list.shuffled().first())
    println(set.shuffled().first())

    println()

    // 使用 扩展文件
    println(list.randomItemValue())
    println(set.randomItemValue())

    println()

    list.randomItemValuePrintln()
    set.randomItemValuePrintln()
}