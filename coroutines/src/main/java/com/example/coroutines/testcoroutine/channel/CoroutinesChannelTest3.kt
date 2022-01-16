package com.example.coroutines.testcoroutine.channel


import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.sync.withPermit
import java.util.concurrent.atomic.AtomicInteger

/**
 *
 * ### 协程并发安全工具
除了我们在线程中常用的解决并发安全的手段之外，协程框架也提供了一些并发安全工具，包括：
1、Channel：并发安全的消息通道。
2、Mutex：轻量级锁，他的lock和unlock从语义上与线程锁比较类似，之所以轻量是因为它在获取不到锁时不会阻塞线程，而是挂起等待锁的释放。
3、Semaphore：轻量级信号量，信号量可以有多个，协程在获取到信号量后即可执行并发操作，当Semaphore的参数为1时，效果等价于Mutex。
 */

fun main() {
    // testNotSafeConcurrent()
    // testSafeConcurrent()
    // testSafeConcurrentTools()
    // testSafeConcurrentTools2()
    testAvoidAccessOuterVariable()
}


//不安全的并发访问
//我们使用线程在解决并发问题的时候总是会遇到线程安全的问题，而Java平台上的Kotlin实现免不了存在并发调度的情况，因此线程安全同样值得留意。
fun testNotSafeConcurrent() = runBlocking {
    var count = 0
    List(1000) {
        GlobalScope.launch { count++ }
    }.joinAll()
    println(count)
}

//java原子操作解决并发安全问题
fun testSafeConcurrent() = runBlocking {
    var count = AtomicInteger(0)
    List(1000) {
        GlobalScope.launch { count.incrementAndGet() }
    }.joinAll()
    println(count.get())
}

//协程并发安全工具Mutex()
fun testSafeConcurrentTools() = runBlocking {
    var count = 0
    val mutex = Mutex()
    List(1000) {
        GlobalScope.launch {
            mutex.withLock {
                count++
            }
        }
    }.joinAll()
    println(count)
}

fun testSafeConcurrentTools2() = runBlocking {
    var count = 0
    val semaphore = Semaphore(1)
    List(1000) {
        GlobalScope.launch {
            semaphore.withPermit {
                count++
            }
        }
    }.joinAll()
    println(count)
}

/**
### 避免访问外部可变变量
编写函数时要求它不得访问外部状态，只能基于参数做运算，通过返回值提供运算结果。
 */
fun testAvoidAccessOuterVariable() = runBlocking {
    var count = 0
    val result = count + List(1000) {
        GlobalScope.async { 1 }
    }.map { it.await() }
        .sum()
    println(result)
}