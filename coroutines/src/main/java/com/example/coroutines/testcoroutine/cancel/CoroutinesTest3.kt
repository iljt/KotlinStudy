package com.example.coroutines.testcoroutine.cancel

import kotlinx.coroutines.*

/**### 协程的取消
  1、取消作用域会取消它的子协程。
  2、被取消的子协程并不会影响其余的兄弟协程。
  3、协程通过抛出一个特殊的异常CancellationException来处理取消操作。
  4、所有kotlinx.coroutines中的挂起函数（withContext、delay等）都是可以取消的。
 */

fun main() {
    //testScopeCancel()
   // testBotherCancel()
   // testCancellationException()
    //testCpuTaskByIsActive()
   // testCpuTaskByEnsureActive()
    //testCpuTaskByYield()
  //  testReleaseResources()
    testNonCancellable()
}


fun testScopeCancel() = runBlocking {
    //runBlocking里面的协程会等待协程执行完毕、会阻塞主线程
      val scope= CoroutineScope(Dispatchers.Default)
       scope.launch {
            delay(1000)
            println("job1 finished")
        }
        //coroutineScope一个协程失败了，所有其他兄弟协程也会被取消
         scope.launch {
            delay(1000)
            println("job2 finished")
        }
     delay(100)
    //取消作用域会取消它的子协程。
     scope.cancel()
     delay(2000)
}


fun testBotherCancel() = runBlocking {
    //runBlocking里面的协程会等待协程执行完毕、会阻塞主线程
    val scope= CoroutineScope(Dispatchers.Default)
    val job1=scope.launch {
        delay(1000)
        println("job1 finished")
    }
    //coroutineScope一个协程失败了，所有其他兄弟协程也会被取消
    val job2= scope.launch {
        delay(1000)
        println("job2 finished")
    }
    delay(100)
    //被取消的子协程并不会影响其余的兄弟协程
    job1.cancel()
    delay(2000)
}


fun testCancellationException() = runBlocking {
    //runBlocking里面的协程会等待协程执行完毕、会阻塞主线程
    //不会打印，因为GlobalScope的作用域没有继承runBlocking的上下文,外面的父协程不会等待GlobalScope.launch启动的子协程结束
    val job1=GlobalScope.launch {
        try {
            delay(1000)
            println("job1 finished")
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
    //让外面的父协程不会等待GlobalScope.launch启动的子协程结束等待，会打印
    delay(100)
   // 协程通过抛出一个特殊的异常CancellationException来处理取消操作
    //job1.cancel()
    //自定义取消操作
    //job1.cancel(CancellationException("取消"))
    //取消和join
    job1.cancelAndJoin()
}


//CPU密集型任务不能取消---使用isActive可以达到取消协程的效果,isActive是一个可以被使用在CoroutineScope中的扩展属性，检查Job是否处于活跃状态
fun testCpuTaskByIsActive() = runBlocking {
   val startTime=System.currentTimeMillis()
   val job=launch(Dispatchers.Default) {
       var newPrintTime=startTime
       var i=0
       //加上isActive后只会打印3次，延時1400ms后isActive=false
       while (i<5 && isActive){
           if (System.currentTimeMillis()>=newPrintTime){
               println("job sleeping ${i++}...")
               newPrintTime+=500
           }
       }
   }
    delay(1400)
    println("main: try to waiting")
    //取消和join
    //job调用取消后会把isActive置为false
    job.cancelAndJoin()
    println("main: can be quit")
}



//CPU密集型任务不能取消---使用ensureActive()可以达到取消协程的效果，如果Job处于非活跃状态，这个方法会立即抛出异常。
fun testCpuTaskByEnsureActive() = runBlocking {
    val startTime=System.currentTimeMillis()
    val job=launch(Dispatchers.Default) {
        var newPrintTime=startTime
        var i=0
        while (i<5){
            ensureActive()
            if (System.currentTimeMillis()>=newPrintTime){
                println("job sleeping ${i++}...")
                newPrintTime+=500
            }
        }
    }
    delay(1400)
    println("main: try to waiting")
    //取消和join
    //job调用取消后抛静默异常（捕获try{}catch{}），并把isActive置为false
    job.cancelAndJoin()
    println("main: can be quit")
}

//CPU密集型任务不能取消--- 使用yield()可以达到取消协程的效果，yield函数会检查所在的协程的状态，如果已经取消，则抛出CancellationException予以响应。此外，它还会尝试让出线程的执行权，给其他协程提供执行的机会。
fun testCpuTaskByYield() = runBlocking {
    val startTime=System.currentTimeMillis()
    val job=launch(Dispatchers.Default) {
        var newPrintTime=startTime
        var i=0
        while (i<5){
            yield()
            if (System.currentTimeMillis()>=newPrintTime){
                println("job sleeping ${i++}...")
                newPrintTime+=500
            }
        }
    }
    delay(1400)
    println("main: try to waiting")
    //取消和join
    //job调用取消后抛静默异常（捕获try{}catch{}），并把isActive置为false
    job.cancelAndJoin()
    println("main: can be quit")
}

//不能取消的任务
//处于取消状态中的协程不能够挂起（运行不能取消的代码），当协程被取消后需要调用挂起函数，我们需要将清理任务的代码放置于NonCancellable CoroutineContext中。这样挂起运行中的代码，并保持协程的取消中状态直到任务处理完成。
fun testNonCancellable() = runBlocking {
    val startTime=System.currentTimeMillis()
    val job=launch(Dispatchers.Default) {
        try {
            repeat(100) {
                println("job :sleeping $it...")
                delay(500)
            }
        }finally {
            //释放资源...
           /* println("job :finally")
            //finally里面不能执行挂起函数
            delay(1000)
            println("job :i am non-cancellable")*/
            //要使协程的取消不影响finally里面的挂起函数的执行需使用NonCancellable
            withContext(NonCancellable){
                println("job :finally")
                delay(1000)
                println("job :i am non-cancellable")
            }
        }

    }
    delay(1400)
    println("main: try to waiting")
    //取消和join
    //job调用取消后抛静默异常（捕获try{}catch{}），并把isActive置为false
    job.cancelAndJoin()
    println("main: can be quit")
}



//协程释放资源
fun testReleaseResources() = runBlocking {
    val startTime=System.currentTimeMillis()
    val job=launch(Dispatchers.Default) {
        try {
            repeat(100) {
                println("job :sleeping $it...")
                delay(500)
            }
        }finally {
            //释放资源...
            println("job :finally")
        }

    }
    delay(1400)
    println("main: try to waiting")
    //取消和join
    //job调用取消后抛静默异常（捕获try{}catch{}），并把isActive置为false
    job.cancelAndJoin()
    println("main: can be quit")
}



