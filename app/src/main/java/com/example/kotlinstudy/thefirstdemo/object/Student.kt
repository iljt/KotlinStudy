package com.example.kotlinstudy.thefirstdemo.`object`

/**

 * Created  by Administrator on 2021/5/30 18:55

 */
//Person() 这里的括号解释
//1.java中子类的构造函数必须调用父类的构造函数，Kotlin也要遵循
//2.子类的主构造函数调用父类的哪个构造函数，在继承的时候通过括号来指定

// name: String , age :Int 这里的student主构造函数不能声明为val
//因为在主构造函数中声明成val或者var的参数将自动变成该类的字段 会导致和父类Person中同名的name和age字段冲突
class Student(val sno: String ,val grade :Int, name: String , age :Int) :Person(name,age) {
   // var sno=""
  //  var grade=0
}