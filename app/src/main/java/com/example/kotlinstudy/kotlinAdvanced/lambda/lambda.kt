package com.example.kotlinstudy.kotlinAdvanced

import java.util.function.Function

/**
   kotlin是全栈语言
   //可用于1、 服务器，iOS，Android，Windows，JS，Gradle Groovy  build.gradle.kts ...
   //     2、native层
 * Created  by Administrator on 2021/12/12 16:49
   // lambda
 */
 fun main(){
    // TODO 下面全部都是函数声明， 既然是函数声明，就不能调用
    //  用lambda去描述函数的声明
    // : (参数)->返回  就是函数的声明
    var method1:()->Unit
    var method2:(Int,String)->Unit
    var method3 : (String, Double) -> Any

    //={参数,参数->表达式}   就是声明与实现结合
   var method01/*:()->Unit*/={ println("我是method01")}
   method01()// 调用函数  method01() 相当于 操作符重载 invoke操作符即method01.invoke()
   method01.invoke()
    var method02 = { "我是method02函数" }
    println(method02())
    //在函数声明时指定参数类型
    var method03:(Int,String)->String={num1,str->num1.toString()+str}
    println(method03(1,"2334"))
   //在函数实现时指定参数类型
   var method05 = {number1: Int, number2: Int -> number1 + number2}
   println(method05(1, 2))


    var method06 : (Int)->String // 先声明
    method06 = fun(value : Int) : String= value.toString() // 后实现
    println(method06(99))

    // 用 先声明 的类型，来 自动推断
    var method07 : (Int)->String // 先声明
    method07 = fun(value)  = value.toString() // 后实现
    println(method07(99))

    var callBack:(Int)->Any
    callBack=fun(value)=value.toString()
    println(callBack(1))

    // 声明 + 实现 一气呵成
    var method08 : (Int) -> String/*左边是声明*/           /*右边是实现*/= {value-> "$value aaa"}
    println(method08(77))

    var method09 : (String, String) -> Unit = {aStr, bStr -> println("aStr:$aStr, bStr:$bStr")}
    method09("aa", "bb")

    var method10 : (String) -> Unit = { /*it ->*/ // 如果你只有一个参数的话，如果你不写，默认就有一个it，他自动会给你增加
        println("你传递进来的值是:$it")
    }
    method10("张三")


    // 没有用到的参数用 _代替，表示拒收
    var method14 : (Int, Int) -> Unit = {_, number2 ->
        println("传递的第二个参数是：$number2")
    }
    method14.invoke(11,1111)


    // === 引用对象的比较    ==值的比较
    var method15 /*: (Char) -> Unit*/ =
        {sex: Char -> if (sex == '男') println("男生") else if (sex == '女') println("女生") else println("未知")}
    method15('男')

    // 需求：传入什么，打印什么 并且返回
    var method16 = {str: Any ->
        println("传入了:$str")
        str  // 最后一行最为返回值
    }
    println(method16(666))

    var method16s = {str: String? /*= "default"*/, str2: String ->
        println("str:$str, str2:$str2")
    }
    method16s(null, "李连杰")

    // String.()  给String类，增加一个匿名函数 == 效果： 我们的 lambda体会持有String本身 == this
    //相当于扩展函数
    var method17:String.()->Unit={
        // this == String本身
        println("你是:$this")
    }
    "888".method17()
    var method18:Int.(Int)->String={ "两数相加结果是:${this + it}" }
    println(11.method18(22))

    var method19 : Double.(Double, Double) -> Unit = {d1, d2 -> println("三数相加结果是:${this + d1 + d2}")}
    method19.invoke(11.1, 22.2, 33.3)
    55.5.method19(22.2, 33.3)


    // 前面为什么用var方法不应该是fun吗？
    // 这是匿名函数，把匿名函数给 method01 变量
    // fun aa{} 与 var aa={}有啥区别
    fun aa() {}
    var aa2 = {}
    // aa就是一个函数，实打实的函数
    // aa2 是接收一个匿名函数的变量而已，这个变量 可以执行这个匿名函数
    // 共同点：他们现象是一样的
    var aa3 = aa2 // 都属于函数引用的概念
    var aa4 = ::aa // 实打实的函数 变成 函数引用 所以可以传递赋值 给另外一个变量


    // kotlin中的 基本上都是表达式 包括 if  可以灵活返回
    // Java中，基本上都是 语句 包括 if  执行体 不可以返回
    var a = if (true) 111 else 222



    // TODO 我们前面一直在玩 参数 输入，  现在我们玩输出(返回类型)
    fun t01() { println(1)} // 默认Unit，除非指定类型
    fun t02() {54656.45} // 默认Unit
    fun t03() {true} // 默认Unit
    fun t04() : String { return "xxx" } // 默认String
    fun t05() : Int { return 99 } // 在函数体里面 return xxx，是无法类型推导的

    // show1和show2都有入参的时候要怎么调用
    // run { 执行你的函数 show1 show2  }
    fun show1() : Boolean = run {
        println(false)
        false } // Boolean    run返回 {}里面的函数返回类型
    fun show2() : ()-> Boolean = {
        println(true)
        true
    } // () -> Boolean    {}里面的函数返回类型
    show1()
    show2()
    show2()() //第二个() 表示执行你函数返回的函数

    // (Int)->Unit
    fun show3() /*: (Int)->Unit*/ = {n1: Any -> println("你的输入类型是int? ${if (n1 is Int) "你是Int类型:$n1" else "你不是Int"}")}
    show3()
    show3()(2)
    show3()("2")

    // (Int,Int) -> Int
   fun ss() : (Int,Int) -> Int = {n1: Int, n2: Int -> println("ss两数相加:${n1 + n2}")
        true
        n1 + n2
    } // lambda返回最后一行的表达式的类型
    ss()(1,2)
    //相当于下面的函数
    var ss1 : (Int,Int) -> Int = {n1: Int, n2: Int -> println("ss1两数相加:${n1 + n2}")
        true
        n1 + n2
    } // lam
    ss1(1,2)


    val methodX1 : (String) -> Int = fun(str) : Int {
        return str.length // 不是lambda体，这个方法体，需要写完return
    }
    // 一般开发不会用 fun关键字 + 声明处

    val methodX2 /*: (String)->Int */ = {str : String -> str.length}
    // 哪种写法比较通用点? = {str : String -> str.length} 因为可以偷懒

    val methodX3 : Function1<String, Int> = {str : String -> str.length}
    methodX3("cbd")



    var method = {number1: Int, number2: Int -> number1 + number2}
    //相当于
    var methods:Function2<Int,Int,Int> = {number1: Int, number2: Int -> number1 + number2}
    println(method(11,22))
    println(methods(11,22))

}