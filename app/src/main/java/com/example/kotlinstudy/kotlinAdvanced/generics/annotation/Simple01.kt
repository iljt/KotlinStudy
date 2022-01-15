package com.example.kotlinstudy.kotlinAdvanced.generics.annotation

// 注解（annotation）
// meta-annotation(元注解) 元注解描述注解的注解  @Target  @Retention
// 作用域的目标
@Target(
    AnnotationTarget.CLASS, // 作用域在类上
    AnnotationTarget.FUNCTION, // 作用域在函数上
    AnnotationTarget.VALUE_PARAMETER, // 作用域在参数上
    AnnotationTarget.EXPRESSION, // 作用域在表达式上
    AnnotationTarget.CONSTRUCTOR, // 作用域在构造函数上
    AnnotationTarget.PROPERTY_SETTER, // 作用域在set上
    AnnotationTarget.PROPERTY_GETTER // 作用域在get上
)
// 保持的策略
@Retention(
    AnnotationRetention.SOURCE // 源码级
    // AnnotationRetention.RUNTIME, // 运行时
)
annotation class Annotation1

// TODO >>>>>>>>>>>>>>>>>>>>>>>>>>>>> 下面是使用注解

@Annotation1
class Simple01 {

    @Annotation1
    fun func1() = println("func1 run ...")

    fun func2(@Annotation1 name : String) = println("func2 run name:$name")

    fun func3() = @Annotation1 10
}

class Simple012 @Annotation1 constructor(age : Int) {

}

class Simple013 {

    var name : Int ? = null
        @Annotation1 set(value) {
            field = value
        }
        @Annotation1 get() = field
}

// TODO 注解的定义与使用
fun main() = println(1)