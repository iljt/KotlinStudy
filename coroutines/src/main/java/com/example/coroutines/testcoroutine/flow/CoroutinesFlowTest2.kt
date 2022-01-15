package com.example.coroutines.testcoroutine.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.system.measureTimeMillis


fun main() {
    //  testBackPresure()
    //testTransformFlowOperator()
    //testlimitLengthOperator()
    // testTerminalOperator()
    //testZipOperator()
    //testFlatMapConcat()
    //testFlatMergeConcat()
    testFlatMapLatest()
}

fun simpleFlowBackPresure() = flow<Int> {
    for (i in 1..5) {
        delay(100)
        emit(i)
        println("emit $i ${Thread.currentThread().name}")
    }
}

/**
### 使用缓冲与flowOn处理背压
buffer(),并发运行流中发射元素的代码。
conflate(),合并发射项，不对每个值进行处理。
collectLatest(),取消并重新发射最后一个值。
当必须更改CoroutineDispatcher时，flowOn操作符使用的缓冲机制，但是buffer函数显式的请求缓冲而不改变执行上下文。
 */
fun testBackPresure() = runBlocking {
    val time = measureTimeMillis {
        simpleFlowBackPresure()
            //.flowOn(Dispatchers.IO)
            //.buffer(50)
            // .conflate()//每次收集跳一个值
            .collectLatest { value ->//只处理最后一个值
                //.collect{value ->
                delay(300)
                println("collect $value thread= ${Thread.currentThread().name}")
            }
    }
    println("collect cost $time")
}

suspend fun performRequest(request: Int): String {
    delay(1000)
    return "reponse $request"
}

/**
 * 转换操作符map、transform
 */
fun testTransformFlowOperator() = runBlocking {
    /*   (1..6).asFlow()
           .map { value -> performRequest(value) }
           .collect {
               println("collect $it")
       }*/

    //变换多个值 模拟多个网络请求与返回
    (1..6).asFlow()
        .transform { value ->
            emit("Making request $value")
            emit(performRequest(value))
            emit("-----request end-----")
        }
        .collect {
            println("$it")
        }
}


fun numbers() = flow<Int> {
    try {
        emit(1)
        emit(2)
        println("this line not execute")
        emit(3)

    } finally {
        println("finally le...")
    }
}

/**
 * 限长操作符
 */
fun testlimitLengthOperator() = runBlocking {
    numbers().take(2).collect { value -> println(value) }
}

/**
### 末端操作符
末端操作符是在流上用于启动流收集的挂起函数。collect是最基础的末端操作符，但是还有另外的一些更加方便使用的末端操作符：
1、转化为各种集合，如toList和toSet。
2、获取第一个（first）值与确保流发射单个（single）值的操作符。
3、使用reduce与fold将流归约到单个值。
 */
fun testTerminalOperator() = runBlocking {
    val sum = (1..5).asFlow()
        .map { value -> value * value }
        .reduce { a, b ->
            a + b
        }
    println(sum)
}

/**
### 组合多个流
就像Kotlin标准库中的Sequence、zip等扩展函数一样，流拥有一个zip操作符用于组合两个流中的相关值。
 */
fun testZipOperator() = runBlocking {
    val nums = (1..3).asFlow().onEach { delay(300) }
    val strs = flowOf("one", "two", "three").onEach { delay(400) }
    val startTime=System.currentTimeMillis()
    nums
        .zip(strs) { a, b ->
            "$a to $b"
        }.collect {
            println("$it at ${System.currentTimeMillis()-startTime} ms from start")
        }
}


fun requestFlow(i:Int)=flow<String>{
    emit("$i : First")
    delay(500)
    emit("$i : Second")
    delay(500)
    emit("$i : Three")

}
/**
### 展平流flatMapConcat
 */
fun testFlatMapConcat() = runBlocking {
    val startTime=System.currentTimeMillis()
    val nums = (1..5).asFlow()
        .onEach { delay(100) }
        //.map { requestFlow(it) }//Flow<Flow<String>> 相当于双重循环了,一般我们使用flatMapConcat 展平一一输出
        .flatMapConcat { requestFlow(it) }//相当于双重循环了我们使用flatMapConcat 展平一一输出
        .collect {
           println("$it at ${System.currentTimeMillis()-startTime} ms from start")
        }
}

/**
### 展平流flatMergeConcat

 */
fun testFlatMergeConcat() = runBlocking {
    val startTime=System.currentTimeMillis()
    val nums = (1..5).asFlow()
        .onEach { delay(100) }
        //.map { requestFlow(it) }//Flow<Flow<String>> 相当于双重循环了我们使用flatMapConcat 展平一一输出
        .flatMapMerge { requestFlow(it) }
        .collect {
            println("$it at ${System.currentTimeMillis()-startTime} ms from start")
        }
}

/**
### 展平流flatMapLatest
 */
fun testFlatMapLatest() = runBlocking {
    val startTime=System.currentTimeMillis()
    val nums = (1..5).asFlow()
        .onEach { delay(100) }
        //.map { requestFlow(it) }//Flow<Flow<String>> 相当于双重循环了我们使用flatMapConcat 展平一一输出
        .flatMapLatest{ requestFlow(it) }
        .collect {
            println("$it at ${System.currentTimeMillis()-startTime} ms from start")
        }
}