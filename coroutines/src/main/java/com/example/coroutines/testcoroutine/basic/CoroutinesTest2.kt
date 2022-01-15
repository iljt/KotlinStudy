package com.example.coroutines.testcoroutine.basic

import kotlinx.coroutines.*

/**### 协程的作用域构建器
 * runBlocking与coroutineScope
 * runBlocking是常规函数，而coroutineScope是挂起函数
 * 它们都会等待期协程体以及所有子协程结束，主要区别在于runBlocking会阻塞当前线程来等待,而coroutineScope只是挂起，会释放底层线程用于其他用途。
 */

fun main() {
  //  testCoroutineScopeBuilder()
    testsupervisorScopeBuilder()
}


fun testCoroutineScopeBuilder() = runBlocking {
    //runBlocking里面的协程会等待协程执行完毕、会阻塞主线程
    //coroutineScope是一个挂起函数，会等待所有的子协程执行完毕，这个作用域才会结束
    coroutineScope {
        val job1= launch {
            delay(1000)
            println("job1 finished")
        }
        //coroutineScope一个协程失败了，所有其他兄弟协程也会被取消
        val job2= async {
            delay(500)
            println("job2 finished")
            "job2 result"
            throw IllegalArgumentException()
        }
    }

}

fun testsupervisorScopeBuilder() = runBlocking {
    //runBlocking里面的协程会等待协程执行完毕、会阻塞主线程
    //supervisorScope是一个挂起函数，会等待所有的子协程执行完毕，这个作用域才会结束
    supervisorScope {
        val job1= launch {
            delay(1000)
            println("job1 finished")
        }
        //supervisorScope一个协程失败了，不会影响其他兄弟协程
        val job2= async {
            delay(500)
            println("job2 finished")
            "job2 result"
            //这里抛异常后不影响job1里面的代码执行
            throw IllegalArgumentException()
        }
    }

}
