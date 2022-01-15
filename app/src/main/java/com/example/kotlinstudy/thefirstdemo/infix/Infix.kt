package com.example.kotlinstudy.thefirstdemo.infix

/**

 * Created  by Administrator on 2021/6/2 23:17

 */
class Infix {

}

fun main(){
    if("Hello Kotlin" beginWith "Hello"){
        println("true")
    }

    val list= listOf("Apple","Banana","Orange","Pear")
    if(list has "Ap"){
        println("true")
    }else{
        println("false")
    }
    val mapto= mapOf("Apple" to 1,"Banana" to 2 ,"Orange" to 3,"Pear" to 4)
    val mapwith= mapOf("Apple" with 1,"Banana" with 2 ,"Orange" with 3,"Pear" with 4)
    for (map in mapto){
        println(map)
    }
    for (map in mapwith){
        println(map)
    }
}

//String的扩展函数内部实现就是调用String类的startWith()函数 加上infix关键字后成了一个infix函数
//这样除了传统的调用方式之外 还可以用一种特殊的语法糖格式调用beginWith()函数
//infix函数有2个严格的限制：1、不能定义成顶层函数必须是某个类的成员函数
//                      2、infix函数必须接收且只能接收一个参数
infix fun String.beginWith(prefix:String)=startsWith(prefix)

//给Collection集合添加一个扩展函数has() Collection是Java和Kotlin所有集合总接口 那么所有的集合的子类就都可以使用这个函数了
infix fun <T> Collection<T>.has(element:T)=contains(element)

//仿写mapOf()的to()函数 实现键值对存值
infix fun <A,B> A.with(that:B):Pair<A,B> = Pair(this,that)



