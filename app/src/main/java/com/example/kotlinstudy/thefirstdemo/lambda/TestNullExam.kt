package com.example.kotlinstudy.thefirstdemo.lambda

/**

 * Created  by Administrator on 2021/5/31 20:50

 */
fun main(){
    //2、如果尝试传一个null 报Null can not be a value of a non-null type Study
    //也就是说Kotlin将空指正的检查提前到了编译期 修正之后才能运行 这样就能保证程序在运行时期不会出现空指针异常了
    //3、如果我们的业务逻辑要求传空怎么办 Kotlin提供了一套可为空的系统 就是在类名的后面加上一个？
    // 所以在doStudy(study:Study?)方法中Study加？ 但是study.readBooks()和study.doHomeWork()在.处报红
    //原因doStudy(study:Study?)的参数改成了 Study？可空所以2个方法都可能造成空指针异常 Kotlin不允许编译通过
    //加?.处理 表示不为null才执行方法

    doStudy(null)
    val len=getTextLength(null)
    //${}字符串内嵌表达式 当表达式中仅有一个变量的时候还可以将两边的大括号省略
    println("$len")
}
fun doStudy(study:Study?){
    //1、这段代码没有空指针风险 因为Kotlin默认所有的参数和变量都不可为空
    //4、这里不能用!!非空断言工具 表示我非常确信这里的对象不会为空 所以不用你来帮我做空指针检查了，如果出现异常，你可以直接抛出空指针异常 后果我自己来承担
    //5、使用!!的地方一般是我们手动判断了对象是不为空的 如下
    if(study!=null){
        study!!.readBooks()
    }
    study?.readBooks()
    study?.doHomeWork()
    //上面两句代码相当于 每次都判空处理了
    if(study!=null){
        study.readBooks()
    }
    if(study!=null){
        study.doHomeWork()
    }
    //我们可以使用let标准函数结合?.一起处理判空处理
    //let函数提供了函数式API的编程接口 并将原始调用对象作为参数传递到Lambda表达式中
    //定义如下 obj.let{obj2->
      //   编写具体业务逻辑
      //   }
    // study?表示对象为空时什么都不做 不为空时调用let函数
    study?.let { stu ->
        stu.readBooks()
        stu.doHomeWork()
    }
    //Lambda简化 当Lambda参数列表中只有一个参数时 可以不用声明参数名 直接使用it关键字代替即可
    study?.let {
        it.readBooks()
        it.doHomeWork()
    }
    //let还可以处理全部变量的判空问题 而if不能

}

//?:表示左边表达式结果不为空就返回左边的表达式结果 否则返回右边表达式结果
fun getTextLength(text:String?)=text?.length?:0