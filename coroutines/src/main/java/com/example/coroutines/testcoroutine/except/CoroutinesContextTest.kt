package com.example.coroutines.testcoroutine.except

import kotlinx.coroutines.*

/**
### 15、协程的上下文
        CoroutineContext是一组用于定义协程行为的元素，他由如下几个元素构成：
        1、Job：控制协程的生命周期。
        2、CoroutineDispatcher：向合适的线程分发任务。
        3、CoroutineName：协程的名称，调试的时候用到。
        4、CoroutineExceptionHandler：处理未被捕捉的异常。
 */

fun main() {
   // testCoroutineContext()
    testCoroutineContextExtend()
}



//组合协程上下文
//有时候我们需要在协程的上下文中定义多个元素。我们可以使用+操作符来实现。如，我们可以显示的指定一个调度器来启动协程并且同时显示指定一个名称。
fun testCoroutineContext() = runBlocking {

    launch(Dispatchers.Default + CoroutineName("xxxCoroutine")) {
        println("thread= ${Thread.currentThread().name}")
    }

}

//组合协程上下文继承
//对于新创建的协程，他的CoroutineContext会包含一个全新的Job实例，它会帮助我们控制协程的生命周期。而剩下的元素会从CoroutineContext的父类继承，
//该父类可能是另外一个协程或者创建该协程的CoroutineScope
fun testCoroutineContextExtend() = runBlocking {
    val scope:CoroutineScope= CoroutineScope(Job() + Dispatchers.IO +CoroutineName("testExtend"))
    val job= scope.launch(Dispatchers.Default + CoroutineName("xxxCoroutine")) {
        //通过scope.launch创建的协程会将scope:CoroutineScope作为父级
        //[ Job ]中括号为get重载操作符
        println("job= ${coroutineContext [ Job ]} thread= ${Thread.currentThread().name}")
        val result=async {
           //通过async创建的协程会将当前协程作为父级
            println("job= ${coroutineContext [ Job ]} thread= ${Thread.currentThread().name}")
            "666"
        }.await()
    }
    job.join()
}

// 协程上下文继承公式
/**
 * 协程的上下文=默认值+继承的CoroutineContext+参数
1、一些元素包含默认值：Dispatchers.Default是默认的CoroutineDispatcher，以及“coroutine”作为默认的CoroutineName；
2、继承的CoroutineContext是CoroutineScope或者父协程的CoroutineContext；
3、传入协程构建器的参数优先级高于继承的上下文参数，因此会覆盖对应的参数值。
 */

fun testCoroutineContextExtend2() = runBlocking {
    val coroutineExceptHandler= CoroutineExceptionHandler { _, throwable ->
        println("${throwable.printStackTrace()}")
    }

    val scope:CoroutineScope= CoroutineScope(Job() + Dispatchers.Main +coroutineExceptHandler)
    val job= scope.launch(Dispatchers.IO ) {

    }
    //最終的父scope:CoroutineScope（CoroutineContext）会包含Dispatches.IO,而不是scope对象里的Dispatches.Main，因为他被协程的构建器里的参数覆盖了。
}

