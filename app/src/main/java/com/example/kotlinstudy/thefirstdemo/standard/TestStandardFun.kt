package com.example.kotlinstudy.thefirstdemo.standard

import java.lang.StringBuilder

/**

 * Created  by Administrator on 2021/6/1 00:19
  标准函数
  1、let函数 主要配合?.操作符进行辅助判空处理 之前null安全使用过
  2、with函数 主要接收2个参数 第1个参数是任意类型的对象 第2个参数是Lambda表达式
     with函数会在Lambda表达式中提供第一个参数对象的上下文，并使用Lambda表达式的最后一行代码作为返回值
     它可以在连续调用同一个对象的多个方法时让代码变得更精简
  3、run函数 需要一个对象去调用 run函数只接收一个Lambda表达式并且会在Lambda表达式中提供调用对象的上下文对象 其他和with函数一样
  4、apply 和run及其相似 也是对象去调用 apply函数只接收一个Lambda表达式并且会在Lambda表达式中提供调用对象的上下文对象
     apply函数无法指定返回值 而是返回调用对象本身

 */

fun main(){
    val list= listOf("Apple","Banana")
    val builder=StringBuilder()
    builder.append("start eating fruit.\n")
    for (fruit in list){
        builder.append(fruit).append("\n")
    }
    builder.append("end eating fruit.")
    val result=builder.toString()
    println("$result")
    println("-----------------")
    //上面连续调用了builder对象的方法 这个时候我们可以考虑使用with函数让代码更简洁
    val result1= with(StringBuilder()){
        append("start eating fruit.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("end eating fruit.")
        toString()
    }
    println("$result1")
    println("-----------------")
    //使用run
    val result2= StringBuilder().run(){
        append("start eating fruit.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("end eating fruit.")
        toString()
    }
    println("$result2")
    println("-----------------")
    //result3返回的是StringBuilder()对象用toString()打印
    // 一般Intent().apply{
     //  intent.putExtra()可以使用putExtra()直接putExtra()就行了 省略代码
    // }
    val result3=StringBuilder().apply {
        append("start eating fruit.\n")
        for (fruit in list){
            append(fruit).append("\n")
        }
        append("end eating fruit.")
    }
    println(result3.toString())
}