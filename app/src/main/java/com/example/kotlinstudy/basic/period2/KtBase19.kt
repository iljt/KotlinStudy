package com.example.kotlinstudy.basic.period2

// TODO 26.Kotlin语言的it关键字特点
fun main() {
    val methodAction : (Int, Int, Int) -> String = { n1, n2, n3 ->
        val number = 24364
        println("$number xxx ,n1:$n1, n2:$n2, n3:$n3")
        "$number xxx ,n1:$n1, n2:$n2, n3:$n3"
    }
    // methodAction.invoke(1,2,3)
    methodAction(1,2,3)

    val methodAction2 : (String) -> String = { "$it xxx" }
    println(methodAction2("788"))

    val methodAction3 : (Double) -> String = { "$it xxx" }
    println(methodAction3(5454.5))
}

/*
    fun methodAction2(it : String) : String { return "$it Derry" }
 */