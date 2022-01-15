package com.example.kotlinstudy.thefirstdemo

import kotlin.math.max

/**
 * 第一行代码Kotlin学习
 * Created  by Administrator on 2021/5/30 18:03
 */
fun main(){
    val a=37
    val b=40
    val value=largeNum(a,b)

    println("large number is $value")
    testif(a,b)
    println("testif large number is $value")
    val score=getScore("tom")
    println("score is $score")
    testfor()
}

//1.比较2个数字大小
/* fun largeNum(a: Int, b: Int): Int {
    return max(a,b)
 }*/
//简写 如果一个函数中只有一行代码，不必写函数体，直接将唯一的一行代码写在函数定义的尾部 并用=相连,return也
//可以省略 等号（=）足以表达返回值的意思 返回值Int可以由max函数推导出来 也可以省略
fun largeNum(a: Int, b: Int)= max(a,b)


//2.Kotlin 中if可以有返回值 返回值是if语句每一个条件中最后一行代码的返回值
/*fun testif(a: Int, b: Int) :Int{
    return if(a>b){
        a
    }else{
        b
    }
}*/
//简写
fun testif(a: Int, b: Int) :Int=if(a>b) a else b

//3.when =表示返回值 可自动推导为Int
fun getScore(name:String)=when(name){
    "tom"->66
    "jim"->77
    "lily"->88
    else -> 0
}

//4.for ..表示左闭右闭区间(升序) until表示左闭右开区间 step每次跳过的元素个数 downto左闭右闭区间(降序)


fun testfor(){
    for(i in 0..10 step 2){
    // println(i)
    }
    for(i in 0 until 5){
        println(i)
    }
    for(i in 10 downTo 1 step 2){
        println(i)
    }
}

