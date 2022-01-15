package com.example.coroutines.testcoroutine.flow

import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


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
    testSimpleFlow()
}

//1、集合
fun testSimpleList():List<Int> = listOf(1,2,3,4,5,6)

//2、序列
//返回了多个值，是同步
fun simpleSeqence():Sequence<Int> = sequence<Int> {
    for (i in 1..6){
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
fun  testMultipleValue() = runBlocking<Unit> {
    multipleValue().forEach { value-> println(value) }
}

suspend fun multipleValue():List<Int>{
    delay(1000)
    return listOf(1,2,3,4,5,6)
}



 fun  testSimpleFlow() = runBlocking<Unit> {
     launch {
         //证明没阻塞
         for (i in 1..6){
             println("I am not blocked")
             delay(1500)
         }
     }
    simpleFlow().collect {value ->
        println(value)
    }
}


//异步返回多个值，一次返回一个值，需要用到Flow
suspend fun simpleFlow()= flow{
    for (i in 1..6){
        delay(1000)
        emit(i)//发射，产生一个元素
    }
}




