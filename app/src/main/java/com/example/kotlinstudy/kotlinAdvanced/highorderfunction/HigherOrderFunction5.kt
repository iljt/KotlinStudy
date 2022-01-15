package com.example.kotlinstudy.kotlinAdvanced.highorderfunction

import java.util.function.Function

/**

 * Created  by Administrator on 2021/12/13 00:06

 */


fun main(){
     //Lambda 历史演化过程
    shows("x", lambda = {
        println("输出:$it")
    })
    shows("xx", {
        println("输出:$it")
    })
    shows("xxx") {
        println("输出:$it")
    }
    shows{
        println("输出:$it")
    }

    show2({
        println("lambda1输出:$it")
    },{
        println("lambda2输出:$it")
    })

    show2({
        println("lambda1输出:$it")
    }){
        println("lambda2输出:$it")
    }
    // 明确的指定，可以把顺序调换
    show2(lambda2 = {
        println("lambda2输出:$it")
    }, lambda1 = {
        println("lambda1输出:$it")
    })

}

// 多lambda
fun show2(lambda1: (Int) -> Unit, lambda2: (Int) -> Unit) {
    lambda1(111)
    lambda2(222)
}


fun shows(name: String = "666", lambda: (String) -> Unit) = lambda.invoke(name)


