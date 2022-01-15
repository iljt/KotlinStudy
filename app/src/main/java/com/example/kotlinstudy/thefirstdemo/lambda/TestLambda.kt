package com.example.kotlinstudy.thefirstdemo.lambda

/**

 * Created  by Administrator on 2021/5/30 23:29

 */
//Lambda演进
fun main(){

    val list= listOf("Apple","Banana","Orange","Pear")
    //minBy传入的是一个Lambda类型参数 根据我们传入的条件来遍历集合 找到该条件下的最小值的元素 maxBy最大值元素
    val maxLengthFruit= list.minByOrNull { fruit: String -> fruit.length }

    //1、当Lambda参数是函数的最后一个参数时 可将Lambda移到函数括号的外面
    val maxLengthFruit1= list.minByOrNull { fruit: String -> fruit.length }
    //2、当Lambda参数是函数的唯一参数时 可将函数的括号省略
    val maxLengthFruit2= list.minByOrNull { fruit:String-> fruit.length}
    //3、由于Kotlin有出色的类型推导机制 Lambda中的参数列表其实在大多数情况下不必申明参数类型
    val maxLengthFruit3= list.minByOrNull { fruit-> fruit.length}
    //4、当Lambda的参数列表只有一个参数时 也不必声明参数名 用it关键字代替
    val maxLengthFruit4= list.minByOrNull { it.length}
    println("maxLengthFruit4=$maxLengthFruit4")

   //1、集合中常用的函数式API map filter
    // map用于将集合中的每个元素都映射成另外的值 ，映射的规则在Lambda中指定，最终生成一个新的集合
    //filter 用来过滤集合中的数据
    val newlist=list.map { it.toUpperCase() }
    for (fruit in newlist){
       // println(fruit)
    }
    val newlist1=list.filter { it.length<=5 }
                     .map { it.toUpperCase() }
    for (fruit in newlist1){
        println(fruit)
    }
    //集合中常用的函数式API any all
    //any函数用于判断集合中是否至少存在一个元素满足指定条件
    //all函数用于判断集合中是否所有元素都满足指定条件
    val anyResult= list.any { it.length<=5 }
    val allResult= list.all { it.length<=5 }
    println("anyResult= $anyResult allResult= $allResult")

    //2、Java中常用的函数式API
    //注意：必须是限定于Kotlin中调用Java中的函数式API
    //定义：如果Kotlin调用一个java方法 ，并且该方法接收一个Java单抽象方法接口参数，就可以使用函数式API。个
    //单抽象方法接口是指接口中只有一个待实现的方法，如果有多个待实现的方法，则无法使用函数式API
    //常见的有Runnable OnclickListener
    //Kotlin中完全舍弃new关键字 创建匿名类实例采用object关键字
    //常规写法
    Thread(object :Runnable{
        override fun run() {
            println("Thread is running")
        }
    }).start()
    //Lambda写法推导
    // 1、Runnable中只有一个待实现的方法 即使没有显示的重写run（）方法 Kotlin也能明白Lambda后面的表达式就是要在run（）中实现的内容
   /* Thread(Runnable{
            println("Thread is running")
    }).start()*/
    // 2、如果一个java函数的参数列表不存在一个以上的Java单抽象方法接口参数，我们还可以将接口名进行省略
  /*  Thread({
        println("Thread is running")
    }).start( )*/
    //3、和之前Kotlin函数式API用法类似 当Lambda表达式是方法的最后一个参数时，可以将Lambda移到方法的外面
 /*   Thread(){ println("Thread is running")}.start( )*/
    //4、当Lambda表达式是方法的的唯一一个参数，可以将括号()省略
    Thread { println("Lambda Thread is running") }.start();
   // 同理我们的button的监听事件也可以这么写 伪代码
   /* button.setOnclickListener{
    }*/
}