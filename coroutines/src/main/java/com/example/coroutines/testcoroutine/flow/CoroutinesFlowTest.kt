package com.example.coroutines.testcoroutine.flow

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


/**
Flow
 */

fun main() {
    testReturnMultipleValue()

}

/**
 * 挂起函数可以异步的返回单个值，但是该如何异步的返回多个值呢？
异步返回多个值的方案：
1、集合
2、序列
3、挂起函數
4、Flow
 */
fun testReturnMultipleValue() {
    // testSimpleList().forEach { value-> println(value) }
    //simpleSeqence().forEach { value-> println(value) }
    //testMultipleValue()
    // testSimpleFlow()
    // testFlowIsCold()
    //testFlowContinuation()
    //testFlowBuild()
    //testFLowContext()
   // testFLowContext2()
    //testFlowLaunch()
    //testCancelFlow()
    testCancelFlowCheck()
}

//1、集合
fun testSimpleList(): List<Int> = listOf(1, 2, 3, 4, 5, 6)

//2、序列
//返回了多个值，是同步
fun simpleSeqence(): Sequence<Int> = sequence<Int> {
    for (i in 1..6) {
        //阻塞
        //  Thread.sleep(1000)
        //不能使用外部的挂起函数，引入后面的Flow
        // delay(1000)
        //yield为Sequence内部提供的挂起函数
        yield(i)
    }
}


//3、挂起函數
////返回了多个值吗，是异步，一次性返回多个值
fun testMultipleValue() = runBlocking<Unit> {
    multipleValue().forEach { value -> println(value) }
}

suspend fun multipleValue(): List<Int> {
    delay(1000)
    return listOf(1, 2, 3, 4, 5, 6)
}


fun testSimpleFlow() = runBlocking<Unit> {
    launch {
        //证明没阻塞
        for (i in 1..6) {
            println("I am not blocked")
            delay(1500)
        }
    }
    simpleFlow().collect { value ->
        println(value)
    }
}


//异步返回多个值，一次返回一个值，需要用到Flow
//3、函数simpleFlow（名为flow的Flow类型构建器函数）不再标有suspend修饰符。即suspend关键字可以省略
/*suspend */fun simpleFlow() = flow {
    for (i in 1..6) {
        delay(1000)
        emit(i)//发射，产生一个元素
    }
}

/**
 * #### Flow和其他方式的区别：
1、名为flow的Flow类型构建器函数 。
2、flow {...}构建快中的代码可以挂起。
3、函数simpleFlow（名为flow的Flow类型构建器函数）不再标有suspend修饰符。
4、流使用emit函数发射值。
5、流使用collect函数收集值。
 */

fun simpleFlow2() = flow<Int> {
    println("Flow started")
    for (i in 1..6) {
        delay(1000)
        emit(i)
    }
}

/**
 * ### 冷流
Flow是一种类似于序列的冷流，flow构建器中的代码直到流被收集的时候才运行。
 */
fun testFlowIsCold() = runBlocking {
    val flow = simpleFlow2()
    println("Calling collect...")
    flow.collect { value -> println(value) }
    println("Calling collect again...")
    flow.collect { value -> println(value) }
}

/**
 * ### 流的连续性
1、流的每次单独收集都是按顺序执行的，除非使用特殊操作符。
1、从上游到下游每个过渡操作符都会处理每个发射出的值，然后再交给末端操作符。
 */
fun testFlowContinuation() = runBlocking<Unit> {
    listOf(1, 2, 3, 4, 5, 6).asFlow().filter {
        it % 2 == 0
    }.map {
        "${it}A"
    }.collect {
        println("collect $it")
    }
}

/**
1、flowOf构建器定义了一个发射固定值集的流。
2、使用.asFlow()扩展函数，可以将各种集合与序列转换为流。
 */
fun testFlowBuild() = runBlocking<Unit> {

    flowOf("a", "b", "c")
        .onEach { delay(1000) }
        .collect { value -> println(value) }


    listOf(1, 2, 3, 4, 5, 6).asFlow()
        .filter {
            it % 2 == 0
        }.onEach { delay(1000) }
        .map {
            "${it}A"
        }.collect {
            println("collect $it")
        }
}

/**
### 流上下文
    1、流的收集总是在调用协程的上下文中发生，流的该属性称为上下文保存。
    2、flow{...}构建器中的代码必须遵循上下文保存属性，并且不允许从其他上下文中发射(emit)。
    3、flowOn操作符，该函数用于更改流发射的上下文。
 */
fun  simpleFlow3()= flow<Int> {
    println("Flow started ${Thread.currentThread().name}")
    for (i in 1..6){
        delay(1000)
        emit(i)
    }
}

fun testFLowContext()= runBlocking {
    simpleFlow3().collect { value ->
        println("collect value= $value thread= ${Thread.currentThread().name}")
    }
}
//flowOn操作符，该函数用于更改流发射的上下文。
fun  simpleFlow4()= flow<Int> {
    //flow里面不能使用 withContext(Dispatchers.IO)来切换线程 要使用flowOn操作符来切换线程
/*    withContext(Dispatchers.IO){
        println("Flow started ${Thread.currentThread().name}")
        for (i in 1..6){
            delay(1000)
            emit(i)
        }
    }*/
        println("Flow started ${Thread.currentThread().name}")
        for (i in 1..6){
            delay(1000)
            emit(i)
        }
}.flowOn(Dispatchers.IO)

fun testFLowContext2()= runBlocking {
    simpleFlow4().collect { value ->
        println("collect value= $value thread= ${Thread.currentThread().name}")
    }
}

/**
    ### 启动流
    使用launchIn替换collect我们可以在单独的协程中启动流的收集。
 */
//事件源
fun event()=(1..6)
    .asFlow()
    .onEach { delay(1000) }
    .flowOn(Dispatchers.Default)

fun testFlowLaunch()= runBlocking {
   val job= event()
        .onEach {    println("thread= ${Thread.currentThread().name}") }
        //必须要collect或者launchIn才能拿到我们的事件数据进行处理
        //.collect {  }
      // .launchIn(CoroutineScope(Dispatchers.IO))//需调用join函数才能执行 因为job协程不是runBlocking主协程的子协程 不会等待job执行完
      // .join()
       .launchIn(this)//在主线程中执行不需要join函数就能执行
    //2.5s后取消
    delay(2500)
    job.cancelAndJoin()

}



fun  simpleFlow5()= flow<Int> {
    for (i in 1..6){
        delay(1000)
        emit(i)
        println("emit $i")

    }
}
/**
### 流的取消
    流采用与协程同样的协作取消，像往常一样，流的收集可以使当流在一个可取消的挂起函数（如：delay）中挂起的时候取消。
 */
fun  testCancelFlow()= runBlocking {
    withTimeoutOrNull(3500){
        simpleFlow5().collect { value -> println(value) }
    }
    println("done")
}


fun  simpleFlow6()= flow<Int> {
    for (i in 1..6){
        emit(i)
        println("emit $i")
    }
}
/**
### 流的取消检测
    1、为方便起见，流构建器对每个发射值执行附加的ensureActive检测以进行取消，这意味着从flow{...}发出的繁忙循环是可以取消的。
    2、处于性能原因，大多数其他流操作不会自动执行其他取消检测，在协程处于繁忙循环的情况下，必须明确检测是否取消。
    3、通过cancellable操作符来执行此操作。
 */
fun  testCancelFlowCheck()= runBlocking {

        /*simpleFlow6().collect { value ->
            println(value)
            if (value==3)cancel()
        }*/
    //这里要取消必须加上cancellable()
    (1..6).asFlow().cancellable().collect{value ->
        println(value)
        if (value==3)cancel()
    }

}