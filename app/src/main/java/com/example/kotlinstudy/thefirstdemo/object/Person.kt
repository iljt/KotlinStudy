package com.example.kotlinstudy.thefirstdemo.`object`

/**

 * Created  by Administrator on 2021/5/30 18:51

 */
//open 可继承关键字
open class Person(val name: String ,val age :Int) {
  //  var name=""
   // var age=0

    fun  eat(){
        //$name $age 内嵌表达式
        println("$name is eating. He is $age years old " )
    }
}