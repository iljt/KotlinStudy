package com.example.kotlinstudy.kotlinAdvanced.diyinnerfun

/**
 * Created  by Administrator on 2021/12/15 23:54
 */

fun main(){

    //run持有最后一行返回的类型
    111.run {
        println(this)
        "2"
        1
    }.run {
        "2"
    }.run {
        true
    }.run {
        println(this)
    }.run {
        println(this)
    }

    //返回的Double表达式最后一行的类型
    var s="111".let {
        "xxx"
        1
        true
        2.33
        it.toDouble()
    }.let {
        1
        true
    }.let {
        1
        "222"
    }.let {
        println(it)
    }
    println(s)

    true.myLet {
        11111
    }.myLet {
        "22"
        println(it)
        1.234
    }.myLet {
        println(it)
        "2233333333333332"
    }.myLet {
        println(it)
    }

    3.myRun{
        "3333333333333333333333333333333333"
    }.myRun{
        println(this)
        true
    }.myRun{
        println(this)
    }.myRun{
        println(this)
    }

    // 特点：let 与 run 区别只有一点   let"(I)"持有it        run"I.()"持有this
    // 1. I.mLet 万能类型.myLet 所有类型都可以调用
    // 2. -> O lambda里面最后一行是true，那就是Boolean
    // 3.  (I)  让lambda持有it
}

inline fun <I,O> I.myLet(block:(I)->O):O=block(this)

inline fun <I,O> I.myRun(block:I.()->O):O=block(this)