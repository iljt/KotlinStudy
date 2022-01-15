package com.example.kotlinstudy.day3.bean

import android.util.Log


class Outer{
    val name = "NAME_Outer"
    // 默认情况就相当于 java 的静态类
    // 如果加了 inner 就是 内部类
    inner class Inner{
        val name = "NAME_Inner"

        fun printName(){
            Log.e("Inner","name = ${this@Outer.name}")
        }
    }
}