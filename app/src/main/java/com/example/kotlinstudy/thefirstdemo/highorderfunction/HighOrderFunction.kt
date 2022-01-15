package com.example.kotlinstudy.thefirstdemo.highorderfunction

import android.content.Context
import java.lang.StringBuilder

/**
 * Created  by Administrator on 2021/6/2 21:36
  高阶函数:如果一个函数接收另外一个函数作为参数，或者返回值的类型是另一个函数，那么该函数就称为高阶函数
 */

fun main(){
  //Kotlin支持多种方式调用高阶函数 比如Lambda表达式、 匿名函数、成员引用 ,Lambda表达式是最常见的高阶函数调用方式
  val num1=100
  val num2=80
  val result1= num1AndNunber2(num1,num2){n1,n2->
    n1+n2
  }
  val result2= num1AndNunber2(num1,num2){n1,n2->
    n1-n2
  }
  println("$result1")
  println("$result2")

 //build函数的用法和apply函数类似 只不过build只作用在StringBuilder类上面而apply作用在所有类上面
  val list= listOf("Apple","Banana","Orange","Pear")
  val result= StringBuilder().build {
    append("start eating fruit.\n")
    for (fruit in list){
      append(fruit).append("\n")
    }
    append("end eating fruit.")
  }
  println("$result")



}
//高阶函数实例1
//(Int,Int)->Int函数类型 ->左边申明函数接收的参数 多个参数用,隔开 ;->右边申明该函数的返回值是什么 没有就使用Unit 相当于java的void
inline fun num1AndNunber2(num1:Int,num2:Int,operation:(Int,Int)->Int):Int {
  val result = operation(num1, num2)
  return result
}

//高阶函数实例2
//在函数类型前面加上ClassName.就表示这个函数类型是定义在哪个类当中的
fun StringBuilder.build(block:StringBuilder.()->Unit):StringBuilder{
   block()
  return this
}

//高阶函数原理我们一直使用的Lambda表达式在底层被转换成了匿名类的实现方式 我们没调用一次Lambda表达式都会创建一个新的匿名类实例，当然也会造成额外的内存和性能开销
//解决办法 使用内联函数功能(高阶函数前面加上inline关键字) 他可以将Lambda表达式带来的运行时开销完全消除
//内联函数工作原理 ：Kotlin编译器会将内联函数中的代码(Lambda表达式代码)在编译的时候自动替换到调用它的地方，这样也就不存在运行时的开销了。
//内联函数可以使用return关键字来进行函数返回，非内联函数(noinline)只能进行局部返回


