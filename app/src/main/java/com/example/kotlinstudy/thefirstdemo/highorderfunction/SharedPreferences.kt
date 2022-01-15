package com.example.kotlinstudy.thefirstdemo.highorderfunction

import android.content.SharedPreferences

/**

 * Created  by Administrator on 2021/6/2 22:32
 高阶函数简化SharedPreferences
 */
//open函数拥有SharedPreferences的上下文并接收一个SharedPreferences.Editor的函数类型参数
fun SharedPreferences.open(block:SharedPreferences.Editor.()->Unit){
    val editor=edit()
    editor.block()
    editor.apply()
}

