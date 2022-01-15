package com.example.kotlinstudy.basic.period3

import java.lang.Exception
import java.lang.IllegalArgumentException

// TODO 41.在Kotlin语法中异常处理与自定义异常特点
fun main() {
   try {
       var info: String? = null

       checkException(info)

       println(info!!.length)

   }catch (e: Exception) {
       println("e=: $e")
   }
}

fun checkException(info: String?) {
   info ?: throw IllegalArgumentException("你的代码太不严谨了")
}

