package com.example.kotlinstudy.thefirstdemo.lazy

/**

 * Created  by Administrator on 2021/6/6 19:06
  泛型实化
  条件 1 、必须是内联函数
      2、在声明的地方加上refiled关键字表示表示该泛型要进行实化
 */
class GenericType {

    //这里实现了java里完全不能实现的功能 getGenericType()返回值直接返回了当前泛型指定的类型
    //T.class这样的语法在java中是不合法的 在Kotlin中借助泛型实化功能就可以实现T::class.java的语法
    inline fun <reified T> getGenericType()=T::class.java
}
fun main(){
    val result1=GenericType().getGenericType<String>()
    val result2=GenericType().getGenericType<Int>()
    println("$result1")
    println("$result2")
}



