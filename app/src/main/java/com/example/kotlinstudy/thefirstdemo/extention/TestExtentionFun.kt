package com.example.kotlinstudy.thefirstdemo.lambda

/**

 * Created  by Administrator on 2021/5/31 22:57
   扩展函数
   定义：在不修改一个类的源码的情况下仍然可以打开这个类 向这个类里面添加新的函数如我们的系统类String Int等
   扩展函数可以定义在任何一个现有类中

 */

fun main(){
    val str:String="xxxx111"
    val count=lettersCount(str)
    val count1= "xxxx111".lettersCount1()
    val str1= "xxxx111".capitalize()//字符串首字母大写
    println("count= $count count1=$count1 str1=$str1")
}

//找出字符串中的字符个数 常规写法
fun lettersCount(str: String):Int{
    var count=0
    for (cha in str){
        if(cha.isLetter()){
            count++
        }
    }
    return count
}
//扩展函数写法
fun String.lettersCount1():Int{
    var count=0
    //lettersCount1定义成了String类的扩展函数 那么函数中就自动拥有了String实例的上下文 所以lettersCount1函数不需要接收字符串参数了 this代表字符串本身
    for (cha in this){
        if(cha.isLetter()){
            count++
        }
    }
    return count;
}
