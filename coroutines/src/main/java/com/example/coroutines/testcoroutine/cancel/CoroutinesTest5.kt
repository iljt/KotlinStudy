package com.example.coroutines.testcoroutine.cancel

import kotlinx.coroutines.*

/**###
  协程的超时任务
  很多情况下取消一个协程的理由是它可能超时
  withTimeoutOrNull通过返回null来进行超时操作，从而替代抛出一个异常
 */

fun main() {
    testTimeOut()
}



//协程的超时任务
fun testTimeOut() = runBlocking {
     //超时会抛出异常
/*    withTimeout(1400){
        repeat(100) {
            println("job :sleeping $it...")
            delay(500)
        }
    }*/

    val result= withTimeoutOrNull(1400){
        repeat(100) {
            println("job :sleeping $it...")
            delay(500)
        }
        "not timeout"
    } ?: "timeout le"

    println(result)
}




