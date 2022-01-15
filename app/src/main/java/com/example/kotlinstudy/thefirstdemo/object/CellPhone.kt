package com.example.kotlinstudy.thefirstdemo.`object`

/**

 * Created  by Administrator on 2021/5/30 20:05

 */
//data 数据类 Kotlin会根据主构造函数中的参数帮忙生成equal()、hashCode()、toString() 方法
//相当于java中的bean
data class CellPhone(val brand:String,val price:Double)

