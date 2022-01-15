package com.example.coroutines.testcoroutine

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**

 * Created  by Administrator on 2022/1/10 23:15

 */

fun main() {
   // testcoroutinesbuilder()
   // testcoroutinesjoin()
   // testcoroutinesawait()
   // testsync()
    //testasync()
    teststartmode()
}

/**
 * 协程构建器
    launch和async构建器都用来启动协程.
    launch:返回一个Job并且不附带任何结果值。
    async：返回一个Deferred，Deferred也是一个Job，可以使用.await()在一个延期的值上得到它的最终结果
 */
fun testcoroutinesbuilder() = runBlocking {
    //runBlocking里面的协程会等待协程执行完毕、会阻塞主线程
   val job1= launch {
        delay(1000)
        println("job1 finished")
   }
    val job2= async {
        delay(1000)
        println("job2 finished")
        "job2 result"
    }
    println(job2.await())
}

//等待一个作业  按顺序执行
fun testcoroutinesjoin() = runBlocking {
    //runBlocking里面的协程会等待协程执行完毕、会阻塞主线程
    val job1= launch {
        delay(3000)
        println("one")
    }
    job1.join()
    val job2= launch {
        delay(1000)
        println("two")
    }

    val job3= launch {
        delay(1000)
        println("three")
    }
}

//等待一个作业 按顺序执行
fun testcoroutinesawait() = runBlocking {
    //runBlocking里面的协程会等待协程执行完毕、会阻塞主线程
    val job1= async {
        delay(3000)
        println("one")
    }
    job1.await()
    val job2= async {
        delay(2000)
        println("two")
    }
    job2.await()
    val job3= async {
        delay(1000)
        println("three")
    }
}


//async组合并发
fun testsync() = runBlocking {
    //runBlocking里面的协程会等待协程执行完毕、会阻塞主线程
    val time= measureTimeMillis {
        val one= doOne()
        val two= doTwo()
        println("result= ${one + two} ")
    }
    println("cost time= $time ms")
}

//async组合并发
fun testasync() = runBlocking {
    //runBlocking里面的协程会等待协程执行完毕、会阻塞主线程
    val time= measureTimeMillis {
        val one=async { doOne() }
        val two= async{ doTwo() }
        println("result= ${one.await() + two.await()} ")
    }
    println("cost time= $time ms")

    val time1= measureTimeMillis {
        //这样写还是2s
        val one1=async { doOne() }.await()
        val two1= async{ doTwo() }.await()
        println("result= ${one1 + two1} ")
    }
    println("cost time1= $time1 ms")

}

private suspend fun doOne():Int{
    delay(1000)
    return 11
}

private suspend fun doTwo():Int{
    delay(1000)
    return 22
}

//协程的启动模式
fun teststartmode() = runBlocking {
/*    val job= launch(start = CoroutineStart.DEFAULT) {
        delay(5000)
        println("Job finished")
    }
    delay(1000)*/
    //runBlocking会等待子协程执行完，但是执行job.cancel()会把launch启动的协程取消，所以不会打印Job finished
   // job.cancel()
    //println("Job canceled")

    //懒加载启动模式 可随时取消
    val joblazy= async (start = CoroutineStart.LAZY) {
        delay(5000)
        22
    }
    //做一些計算。。。
    println(joblazy.await())

    //协程创建后立即在当前函数调用栈中执行，直到遇到第一个真正挂起的点。
    val job1= async (context = Dispatchers.IO, start = CoroutineStart.UNDISPATCHED) {
       //会在当前函数runBlocking主线程中执行
        println("Thread: ${Thread.currentThread().name}")
    }

}