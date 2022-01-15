package com.example.coroutines.testcoroutine.flow

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



