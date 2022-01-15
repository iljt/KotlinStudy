package com.example.kotlinstudy.thefirstdemo.extention

import java.lang.StringBuilder

/**

 * Created  by Administrator on 2021/5/31 23:18
   操作符重载 operator关键字
 */
fun main(){
    val money1=Money(5)
    val money2=Money(15)
    val money3=money1+money2
    println("${money3.value}")
    val money4=money3+100
    println("${money4.value}")
    var str2="cbd"*3
    println("$str2")

}

//操作符重载+
class  Money(val value :Int){
    //Money+Money
    operator fun plus(money: Money):Money{
        val sum=value+money.value
        return Money(sum)
    }
    //Money+数字
    operator fun plus(newVlue: Int):Money{
        val sum=value+newVlue
        return Money(sum)
    }
}

//扩展函数和操作符重载* 重复字符串n次 一般的写法
/*operator  fun String.times(n:Int):String{
    var builder =StringBuilder()
    repeat(n){
        builder.append(this)
    }
    return builder.toString()
}*/
//String类中已经提供了一个用于将字符串重复n遍的repeat()函数
operator  fun String.times(n:Int)= repeat(n)

