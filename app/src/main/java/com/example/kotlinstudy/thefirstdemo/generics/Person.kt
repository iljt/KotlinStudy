package com.example.kotlinstudy.thefirstdemo.generics

/**

 * Created  by Administrator on 2021/6/6 23:41

 */
open class Person(val name:String,val age: Int) {

}

class Student(name:String,age :Int):Person(name,age)
class Teacher(name:String,age :Int):Person(name,age)
