package com.example.kotlinstudy.basic.period6

class MyObject {

    companion object {

        //java端直接MyObject.TARGET
        @JvmField
        val TARGET = "公园"

        @JvmStatic
        fun showAction(name: String) = println("$name 要去 $TARGET 玩")
    }

}

// TODO 134-注解@JvmStatic与Kotlin关系
fun main() {
    // KT 端
    MyObject.TARGET

    MyObject.showAction("cbd")
}