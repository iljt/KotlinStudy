package com.example.coroutines.testcoroutine.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.RuntimeException
import kotlin.system.measureTimeMillis

/**
 * #### 流的异常处理
当运算符中的发射器或者代码抛出异常时，有几种处理异常的方法：
1、try/catch块
2、catch函数
 */

fun main() {
    //testFlowExcept()
   // testFlowExcept2()
   // testFlowRetry()
   // testFlowFinally()
    testFlowOnCompletion()
}


fun flowExcept() = flow<Int> {
    for (i in 1..3) {
        println("emitting $i")
        emit(i)
    }
}

/**
flow 异常
try/catch块 捕获下游的异常
 */
fun testFlowExcept() = runBlocking {
    try {
        flowExcept().collect { value ->
            println(value)
            check(value <= 1) { println("collected $value") }
        }
    } catch (e: Throwable) {
        println("Caught $e")
    }
}

/**
flow 异常
catch函数 捕获上游的异常
 */
fun testFlowExcept2() = runBlocking {
/*    flow {
        emit(1)
        throw ArithmeticException("div 0")
    }.catch { e: Throwable ->
        println("Caught $e")
    }.flowOn(Dispatchers.IO).collect {
            println("collect $it ${Thread.currentThread().name}")
        }*/

    flow {
        throw ArithmeticException("div 0")
        emit(1)
    }.catch { e: Throwable ->
            //补救措施
            println("Caught $e thread=${Thread.currentThread().name}")
            emit(666)
        }
     .flowOn(Dispatchers.IO).collect {
            println("collect $it thread=${Thread.currentThread().name}")
     }
}

/**
flow 重试
 */
fun testFlowRetry() = runBlocking {

    flow {
        emit(1)
        throw ArithmeticException("div 0")
    }.retry(3) { e: Throwable ->//重试
        println("retry Caught $e thread=${Thread.currentThread().name}")
        delay(1000)
        true
    }.catch { e: Throwable ->
        println("Caught $e")
    }.flowOn(Dispatchers.IO).collect {
            println("collect $it thread=${Thread.currentThread().name}")
        }
}

/**
    #### 流的完成
    当流收集完成时（普通情况或异常情况），它可能需要执行一个动作。
    1、命令式finally块。
    2、onCompletion声明式处理。
 */

fun finallyFlow()=(1..3).asFlow()

fun flowOnCompletion() = flow<Int>{
    emit(1)
    throw RuntimeException("xxx")

}

fun testFlowFinally() = runBlocking {
    try {
        finallyFlow().collect{
            println(it)
        }
    }finally {
        println("finally")
    }
}


fun testFlowOnCompletion() = runBlocking {

        flowOnCompletion()
            .onCompletion { except->
                except ?: println("Flow complete exceptionally")
            }
            .catch { e: Throwable ->
                println("Caught $e")
            }
            .collect{
            println(it)
        }

}

