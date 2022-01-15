// TODO 高阶函数
fun main() {
    println( toast()(11,"aa"))

    println("-------------------------")
    log()({num,num2->
        "两数相加的结果是:${num + num2}"
    },"ssss")
    log2()("dnf"){num,num2->
        "两数相加的结果是:${num + num2}"
    }
    println("----------------------------------------")
    test02()("xfd",3,{
        println("第1个lambda的参数是:$it")
    },{
        println("第2个lambda的参数是:$it")
    })
    println("----------------------------------------")
    // 输入String 返回Int == RxJava map 变换操作符
    testMap<String, Int, Int, String>()("xxx", 99, {
        // 变换操作：把String变成Int
        "$it".length
    }) {
        // 变换操作：把Int变成String
        "yyy $it"
    }
}



//函数返回函数
 fun toast()={num:Int,str:String ->
    true
    num
    str
    "姓名是:$str, 年龄是:$num"
}

// 函数返回函数，而这个函数是高阶函数
fun  log()={ lambda:(Int,Int)->String,str:String ->
    false
    '男'
    val lambda = lambda(6, 6)
    println("最后我组装的值是:$lambda + $str")
}

fun  log2()={ str:String ,lambda:(Int,Int)->String->
    false
    '男'
    val lambda = lambda(6, 6)
    println("最后我组装的值是:$lambda + $str")
}


/*------函数返回类型判断---------------*/
// study返回类型是什么  (String, Int) -> Int  ==== -> Int为表达式中最后一行的类型
fun study() = { str: String, num: Int ->
    true
    1
}

// (String, Int, (Int)-> Unit) -> Unit
fun test() : (String, Int, (Int)-> Unit) -> Unit = fun(str: String, num: Int, lambda: (Int) -> Unit) {
    str
    num
    true // fun{} 此{} 是函数体，不是我们前面的那种 lambda，所以没有明确指定，他就是Unit
}

// test02 返回类型是什么 函数 (String, Int, (String)-> Unit, (Int)->Unit) -> Unit
fun test02() = {str: String, num: Int, lambda1: (String) -> Unit, lambda2 : (Int) -> Unit ->
    lambda1(str)
    lambda2(num)
}


fun <T1, T2, R1, R2> testMap()={str: T1, num: T2,lambda1:(T1)->R1,lambda2:(T2)->R2->
    println("调用了第一个lambda:${lambda1(str)}  ${if (lambda1(str) is Int) "你变换后是Int" else "你变换后不是Int"}")
    println("调用了第二个lambda:${lambda2(num)} ${if (lambda2(num) is String) "你变换后是String" else "你变换后不是String"}")
}


