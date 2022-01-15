package com.example.kotlinstudy.thefirstdemo.lazy

import kotlin.reflect.KProperty

/**

 * Created  by Administrator on 2021/6/6 12:47
  委托属性
  核心思想是将一个属性(字段)的具体实现交给一个类去完成
  当给属性赋值的时候会调用类里面的setValue()方法（相当于用到时才去赋值---懒加载） 取值的时候调用类里面的getValue()方法
  当委托属性用val修饰时 类不用实现setValue()方法 因为val修饰的变量在初始化之后无法被赋值
 */
//实现自己的一个懒加载 类似lazy函数
class Later<T>(val block:()->T) {
    //我们平时开发时用到的by lazy中 by是关键词 lazy是一个高阶函数 lazy函数中会创建并返回一个Delegate对象
    //当我们调用p属性的时候，其实是调用的Deletegate对象的getValue()函数 然后getValue()函数又会调用lazy函数传入的
    //Lambda表达式 这样表达式中的代码就可以得到执行了  并且调用p属性后得到的值就是Lambda表达式中最后一行代码的返回值
   // val p by lazy {  }
    //Any?表示我们的Later委托功能在任何类中都可以使用
    var  value:Any?=null

    operator fun getValue(any: Any?,pro:KProperty<*>):T{
        if(value==null){
            value= block()
        }
        return any as T
    }

    //由于懒加载技术不会对属性进行赋值所以不用实现setValue()方法
    //为了让他的用法类似lazy函数 最好再定义一个顶层函数 写在Later类的外面，因为只有不定义在任何类中的函数才是顶层函数
}

fun <K>  later(block: () -> K)=Later(block)

