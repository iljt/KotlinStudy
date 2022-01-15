package com.example.kotlinstudy.kotlinAdvanced.diyinnerfun

import java.io.File

/**

 * Created  by Administrator on 2021/12/15 23:30

 */
fun main(){
 /*   File("D:\\a.txt").apply {
        setReadable(true)
    }.apply {
        setReadOnly()
    }.apply {
       readLines()
    }.apply {
        print(this)
    }*/

    "11".myApply {
        this+"22"
    }.myApply {
        //this 指的"11"本身
        this+"33"
        println(this.length)
    }.myApply {
        //this 指的"11"本身
        this+"44"
        println(this.length)
    }
    111.myApply {
        println(this+111)
    }.myApply {
        println(this+222)
    }.myApply {
        println(this+333)
    }
}
// 只要是高阶函数，必须用inline修饰，因为内部会对lambda做优化
// I.myApply 万能类型.myApply 所有类型都可以调用
// lambda : I.()  对I泛型进行了匿名函数扩展  好处 lambda持有this == I == "xxx"
// : I 为了链式调用
//  lambda() 调用执行
inline fun <I> I.myApply(block:I.()->Unit):I {
    block()
    return this
}