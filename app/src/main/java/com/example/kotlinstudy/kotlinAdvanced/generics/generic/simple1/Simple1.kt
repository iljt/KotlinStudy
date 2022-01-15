package com.example.kotlinstudy.kotlinAdvanced.generics.generic.simple1

// T? 可以传null
class MyStudy<T>(type: T) {
    var item : T = type
}

// TODO 泛型快速入门先
fun main() {
    // 1.完整写法
    val myStudy : MyStudy<String> = MyStudy<String>("Derry is OK")
    println(myStudy.item)

    val myStudy2 : MyStudy<Boolean> = MyStudy<Boolean>(false)
    println(myStudy2.item)

    // 2.泛型 的 类型推断
    val myStudy3  = MyStudy("Derry is OK")  // 把T推断成 String
    println(myStudy3.item)

    val myStudy4  = MyStudy(true) // 把T推断成 Boolean
    println(myStudy4.item)
}