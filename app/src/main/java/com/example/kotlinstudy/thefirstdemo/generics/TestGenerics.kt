package com.example.kotlinstudy.thefirstdemo.generics

/**

 * Created  by Administrator on 2021/6/6 12:02

 */
class TestGenerics {

}

fun  main(){
   val myClass= MyClass<Int>()
    val result=myClass.method(124)
    val myClass2= MyClass2()
    //泛型方法在在调用method2()方法的时候指定泛型类型
    val result2=myClass2.method2<Int> (66666)
    // Kotlin有非常出色的类型推导机制 这里可以省略泛型类型的指定
    val result3=myClass2.method2 ("66666")
    println(result)
    println(result2)
    println(result3)

}