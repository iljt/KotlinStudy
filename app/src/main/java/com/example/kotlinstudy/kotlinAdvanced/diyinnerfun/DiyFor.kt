package com.example.kotlinstudy.kotlinAdvanced.diyinnerfun


fun main() {
    // TODO 官方的for：
    var list=listOf("aa", "bb", "cc").filter {
        //过滤不包含a的
        !it.contains("a")
    }.map {
       it+" xxx"
    }/*.forEach {
        println(it)
    }*/
    println(list)

    println("--------------")

    var mylist=listOf("aaaa", "bbbb", "cccc")
        .myForeach{
            println(it)
    }
    println("---------------------------")
    var mylist2=listOf("aaaa", "bbbb", "cccc")
        .myForeach2{
            println(this)
        }

    println("--------------------------------------")
    var mylist3=listOf("aaaa", "bbbb", "cccc")
        .myForeach3{
            println(this)
        }
}

// Set List Map .... 父类  Iterable
private inline fun <T> Iterable<T>.myForeach(lambda:(T)->Unit){
   for (item in this){
       lambda(item)
   }
}

// run 就是执行你自己的逻辑
private inline fun <T> Iterable<T>.myForeach2(lambda:T.()->Unit)=run{for (item in this) lambda(item)}

private inline fun<T> Iterable<T>.myForeach3(lambda : T.() -> Unit) = myrun {  for (item in this) lambda(item) }

private inline fun <T, R> T.myrun(block: T.() -> R): R=block(this)
