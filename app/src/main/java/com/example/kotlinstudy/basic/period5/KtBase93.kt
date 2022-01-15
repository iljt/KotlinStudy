package com.example.kotlinstudy.basic.period5

// 普通类 解构声明
class Student1(var name: String , var age: Int, var sex: Char,var height:Int) {

    // 注意事项：component0 顺序必须是 component1 component2 component3 和成员一一对应，顺序下来的
    operator fun component1() = name
    operator fun component2() = age
    operator fun component3() = sex
    operator fun component4() = height
}

// 数据类
data class Student2Data(var name: String , var age: Int, var sex: Char,var height:Int)

// TODO 93.Kotlin语言的解构声明学习
fun main() {
    val(name, age, sex,height) = Student1("李四", 89, '男',188)
    println("普通类 结构后:name:$name, age:$age, sex:$sex,height:$height")

    val(name1, age1, sex1,height1) = Student2Data("李四", 89, '男',188)
    println("数据类 结构后:name:$name1, age:$age1, sex:$sex1,height:$height1")

    val(_, age2, _,_) = Student1("李四", 89, '男',188)
    println("数据类 结构后: age2:$age2")
}