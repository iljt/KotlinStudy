package com.example.kotlinstudy.thefirstdemo.lazy

/**

 * Created  by Administrator on 2021/6/6 12:37
   类委托 Kotlin中委托关键字by 在接口（Set<T> ）后面使用by 再接上受委托的辅助对象（helpset）
   核心思想是将一个类的具体实现委托给另外一个类（HashSet<T>）去完成 类似java的静态代理
 */
class MySet<T>(val helpset:HashSet<T>) :Set<T> by helpset  {
    //这样MySet<T>除了能够使用HashSet里面的所有功能 还能自定义自己的方法
    fun hellword()= println("Hello World")

}