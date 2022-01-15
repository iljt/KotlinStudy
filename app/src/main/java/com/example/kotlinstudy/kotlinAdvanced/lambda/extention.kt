package com.example.kotlinstudy.kotlinAdvanced

/**

 * Created  by Administrator on 2021/12/12 17:00
 * 扩展函数

 */

//定义为顶层函数，任何地方都可调用
// public static shows
// kotlin默认是公开的public
fun String.show(){
    // 你对谁扩展  this==谁本身
    // String == this
    println("持有的String本身是:$this")
}


// public static Object toast(toast$this)
fun Any.toast(){
    // Any == this
    println("你调用了我，你的值是:$this")
}


