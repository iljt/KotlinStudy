package com.example.coroutines.testcoroutine.except

import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.*
import java.lang.NullPointerException

/**
###
 */

@RequiresApi(Build.VERSION_CODES.KITKAT)
fun main() {
    //testCoroutineRootExceptBrocast()
   // testCoroutineNotRootExceptBrocast()
    //testSuperVisorJob()
   // testSuperVisorScope()
   // testSuperVisorScope2()
  //  testCoroutineExceptHandler()
   // testCoroutineExceptHandler2()
    //testCancelAndExcept()
    //testCancelAndExcept2()
    testExceptAggregation()
}



//根协程上异常传播
//自动传播异常(launch和actor)，向用户暴露异常（async与produce）当这些构建器用于创建一个根协程时（该协程不是另一个协程的子协程），
//前者这类构建器，异常会在它发生的第一时间被抛出，而后者则依赖用户最终来消费异常，如通过await或receive。
fun testCoroutineRootExceptBrocast() = runBlocking<Unit> {
   //GlobalScope启动的是根协程，而单纯的launch不是根协程了而是runBlocking下面的子协程
    //val job= launch(Dispatchers.Default ) {
   val job= GlobalScope.launch(Dispatchers.Default ) {
       try {
           //自动传播的异常 需在抛出异常的时候try catch
           throw IndexOutOfBoundsException()
       }catch (e:Exception){
           println("自动传播的异常")
           println( e.printStackTrace())
       }
    }
    job.join()
    //val deferred=async {
    val deferred=GlobalScope.async {
        throw NullPointerException()
    }
    //向用户暴露的异常 需在await()或者receive()的时候try catch,否则捕获不到
  try {
        deferred.await()
    }catch (e:Exception){
        println("向用户暴露的异常")
        println( e.printStackTrace())
    }
    delay(1000)
}


//### 非根协程异常
//其他协程所创建的协程中，产生的异常总是会被传播。
fun testCoroutineNotRootExceptBrocast() = runBlocking<Unit> {
  val scope= CoroutineScope(Job())
  val job= scope.launch {
      val deferred= async {
           //如果非根协程async抛出异常，launch就会立即抛出异常，而不会调用.await()
           throw IllegalArgumentException()
          "end"
       }
      println("--------")
      //抛異常后不會調用
      val result=deferred.await()
      println(result)

  }
    job.join()
}


/**
 * 异常的传播特性
当一个协程由于一个异常而运行失败时，他会传播这个异常并传递给他的父级。接下来父级会进行下面几步操作：
1、取消它自己的子级。
2、取消它自己。
3、将异常传播并传递给它的父级。。
  */
fun testSuperVisorJob() = runBlocking<Unit> {
    //SupervisorJob()协作式 一个协程异常了不影响其他子协程,它不会传播异常给它的父级，它会让子协程自己处理异常。
    //Job() 一个协程异常了其他协程也会挂掉
    val supervisorScope= CoroutineScope(SupervisorJob())
   // val supervisorScope= CoroutineScope(Job())
    val job1= supervisorScope.launch {
         delay(1000)
         println("job1")
         throw  NullPointerException()
    }
    val job2= supervisorScope.launch {
       try {
           delay(Long.MAX_VALUE)
       }finally {
           println("job2 finished")
       }
        throw  NullPointerException()
    }
    joinAll(job1,job2)
}

//#### SupervisorScope

fun testSuperVisorScope() = runBlocking<Unit> {
    //相当于val supervisorScope= CoroutineScope(SupervisorJob())
   // job1里面抛异常不影响job2
    supervisorScope {
        val job1= launch {
            delay(1000)
            println("job1")
            throw  NullPointerException()
        }
        val job2= launch {
            try {
                delay(Long.MAX_VALUE)
            }finally {
                println("job2 finished")
            }
        }
        delay(1000)
    }

}

//当作业自身执行失败的时候，所有的子作业将会被全部取消
fun testSuperVisorScope2() = runBlocking<Unit> {
    //相当于val supervisorScope= CoroutineScope(SupervisorJob())
    supervisorScope {
        val child= launch {
            try {
                println("child is sleeping")
                delay(Long.MAX_VALUE)
            }finally {
                println("child is canceled")
            }

        }
        //让出cpu执行权 让cilid有机会执行
        yield()
        println("throw a except from the scope ")
        throw AssertionError()
    }

}

/**
 * ### 异常的捕获
    使用CoroutineExceptionHandler对协程的异常进行捕获。
    以下的条件被满足时，异常就会被捕获：
    1、时机：异常是被自动抛出异常的协程所抛出的(使用launch而不是async)
    2、位置：在CoroutineScope的CoroutineContext中或在一个根协程（CoroutineScope或者SupperVisorScope的直接子协程）中。
 */

fun testCoroutineExceptHandler() = runBlocking<Unit> {

    val handlerException= CoroutineExceptionHandler { _, throwable ->
        println("Caugth: $throwable")
    }
    val job= GlobalScope.launch(handlerException) {
        throw IllegalArgumentException()
    }
    val deferred= GlobalScope.async(handlerException) {
        throw NullPointerException()
    }
    job.join()
    deferred.await()

}

//异常常见错误
fun testCoroutineExceptHandler2() = runBlocking<Unit> {

    val handlerException= CoroutineExceptionHandler { _, throwable ->
        println("Caugth: $throwable")
    }
    val scope= CoroutineScope(Job())
    //在这里可以捕获到
    val job= scope.launch(handlerException) {
        //在这里捕获不到
        launch/*(handlerException)*/ {
            throw IllegalArgumentException()
        }
    }
    job.join()

}


//#### 取消与处理
//1、取消与异常紧密相关，协程内部使用CancellationException来进行取消，这个异常会被忽略。
//2、当子协程被取消时，不会取消它的父协程。
fun testCancelAndExcept() = runBlocking<Unit> {

    val job= launch{

       val child= launch {
            try {
                delay(Long.MAX_VALUE)
            }catch (e:Exception){
                println("Child is cancelled.")
            }
        }
        yield()
        println("Cancelling  Child.")
        child.cancelAndJoin()
        yield()
        println("Parent is not Cancelled.")
    }
    job.join()
}

//3、如果一个协程遇到了CancellationException以外的异常，它将使用该异常取消它的父协程，当父协程的所有子协程都结束后，异常才会被父协程处理。
fun testCancelAndExcept2() = runBlocking<Unit> {
    //异常的捕获
     val exceptHandler= CoroutineExceptionHandler { _, throwable ->
        println("Caugth: $throwable")

    }
    val job= GlobalScope.launch(exceptHandler){

        val child= launch {
            try {
                println("Child is run")
                delay(Long.MAX_VALUE)
            }catch (e:Exception){
               //要使协程的取消不影响finally里面的挂起函数的执行需使用NonCancellable
                withContext(NonCancellable){
                    println("Child is cancelled. but exception is not handled until all children terminate")
                    delay(1000)
                    println("The first Child finished its non cancellation block.")

                }
            }
        }

        launch {
            delay(100)
            println("Second Child throw an exception")
            throw ArithmeticException()
        }

    }
    job.join()
}


//### 异常聚合
//当协程的多个子协程因为异常而失败时，一般情况下取第一个异常进行处理，在第一个异常之后发生的其他所有异常，都将被绑定到第一个异常之上。
@RequiresApi(Build.VERSION_CODES.KITKAT)
fun testExceptAggregation() = runBlocking<Unit> {
    //异常的捕获
    val exceptHandler= CoroutineExceptionHandler { _, throwable ->
        println("Caugth: first except= $throwable other except= ${throwable.suppressed.contentToString()}")

    }
    val job= GlobalScope.launch(exceptHandler){

        val child= launch {
            try {
                println("Child is run")
                delay(Long.MAX_VALUE)
            }catch (e:Exception){
                throw ArithmeticException()
            }
        }
        launch {
            try {
                delay(Long.MAX_VALUE)
            }catch (e:Exception){
                throw IndexOutOfBoundsException()
            }
        }

        launch {
            delay(100)
            println("the first throwed exception")
            throw NullPointerException()
        }

    }
    job.join()
}