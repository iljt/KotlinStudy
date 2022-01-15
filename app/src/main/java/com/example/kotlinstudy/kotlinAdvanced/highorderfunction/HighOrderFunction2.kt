package com.example.kotlinstudy.kotlinAdvanced.highorderfunction

/**

 * Created  by Administrator on 2021/12/12 23:52
   高阶函数 函数中有lambda就是属于高阶函数， 函数的函数就是高阶函数
 */

fun main(){
   var str= show(66){
        println(it)
       "return value= $it"
    }
    println(str)
    var plus=dealNum(10,2){n1,n2->
        n1+n2
    }
    println(plus)
    var jian=dealNum(10,2){n1,n2->
        n1-n2
    }
    println(jian)
    var mutl=dealNum(10,2){n1,n2->
        n1*n2
    }
    println(mutl)
    var chu=dealNum(10,2){n1,n2->
        n1/n2
    }
    println(chu)
}


fun show(number:Int,lambda:(Int)->String)=lambda.invoke(number)

// 高阶函数处理2个数的加减乘除
fun dealNum(number1: Int,number2: Int,lambda: (Int, Int) -> Int)=lambda.invoke(number1,number2)