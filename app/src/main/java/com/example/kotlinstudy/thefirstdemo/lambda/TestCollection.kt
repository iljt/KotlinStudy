package com.example.kotlinstudy.thefirstdemo.lambda

/**

 * Created  by Administrator on 2021/5/30 20:33

 */

fun  main(){
    //listOf不可变集合
    val list= listOf("Apple","Banana","Orange")
    for (fruit in list){
      //  println(fruit)
    }
    //listOf可变集合
    val mutableList= mutableListOf("Apple","Banana","Orange")
    mutableList.add("Pear")
    for (fruit in mutableList){
     //   println(fruit)
    }

    //Set集合底层使用hash映射机制来存放数据 所以语速无法保证有序 这是和List集合最大的不同之处
    //setOf不可变集合
    val set= setOf("Apple","Banana","Orange")
    for (fruit in set){
      //  println(fruit)
    }
    //mutableSetOf可变集合
    val mutableset= mutableSetOf("Apple","Banana","Orange")
    mutableset.add("Pear")
    for (fruit in mutableset){
      //  println(fruit)
    }

    val map=HashMap<String,Int>()
    map["Apple"]=1
    map["Banana"]=2
    map["Orange"]=3
    //简写 to不是关键字 而是infix函数
    val map1= mapOf("Apple" to 1,"Banana" to 2,"Orange" to 3)

    for (fruit in map){
          println(fruit)
    }
    for (fruit in map1){
        println(fruit)
    }
}