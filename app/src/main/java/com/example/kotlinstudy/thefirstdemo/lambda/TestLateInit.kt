package com.example.kotlinstudy.thefirstdemo.lambda

/**

 * Created  by Administrator on 2021/5/31 21:44

 */
fun main(){
    //1、lateinit 关键字告诉Kotlin编译器 会在晚些时候对变量进行初始化 这样就不用一开始的时候赋值为空 否则的话Kotlin要求必须初始化变量(一般为空或者0)
    //但一定要保证被lateinit修饰的变量在被调用之前已经完成初始化 否则报错lateinit property name has not been initialized
    lateinit var name:String
    name="sss"
    println( "name is $name")
   // 2、sealed关键字表示密封类关键字 当用when判断时 可以去掉我们编写无用条件下的分支代码
}