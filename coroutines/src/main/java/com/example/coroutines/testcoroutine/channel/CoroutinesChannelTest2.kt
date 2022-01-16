package com.example.coroutines.testcoroutine.channel

import com.example.coroutines.api.User
import com.example.coroutines.api.userServiceApi
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.selects.select
import java.io.File

/**
 * Channel
 */

fun main() {
    //testSelectChannel()
    //testSelectClause()
    //testSelectClause2()
   testSelectFlow()
}


/**
### 复用多个Channel
跟await类似，会接收到最快的那个channel消息
 */
fun testSelectChannel() = runBlocking {
    val channels = listOf(Channel<Int>(), Channel<Int>())
    //生产者
    GlobalScope.launch {
        delay(100)
        channels[0].send(666)
    }

    GlobalScope.launch {
        delay(250)
        channels[1].send(888)
    }
    //消费者 跟await类似，会接收到最快的那个channel消息
    GlobalScope.launch {
        val result = select<Int?> {
            channels.forEach { channel ->
                channel.onReceive { it }
            }
        }
        println(result)
    }.join()
}

/**
### SelectClause
我们怎么知道哪些事件是可以被select的呢？其实所有能够被select的事件都是SelectClauseN类型，包括：
1、SelectClause0：对应事件没有返回值，例如join没有返回值，那么onJoin就是SelectClauseN类型，使用时onJoin()的参数是一个无参函数。
2、SelectClause1：对应事件有返回值，如onReceive（）和onAawait（）都是此类情况。
3、SelectClause2：对应事件有返回值，此外还需要一个额外的参数，如Channel.onSend有两个参数，第一个是Channel数据类型的值，表示即将发送的值，第二个是发送成功的回调参数。

如果我们确认挂起函数是否支持select，只需要查看其是否存在对应的SelectClauseN类型可回调即可。
 */
fun testSelectClause() = runBlocking {

    val job1 = GlobalScope.launch {
        delay(100)
        println("job1")
    }

    val job2 = GlobalScope.launch {
        delay(200)
        println("job2")
    }
    select<Unit> {
        //先执行完的任务先打印
        job1.onJoin { println("job1 onJoin") }
        job2.onJoin { println("job2 onJoin") }
    }
    delay(1000)
}


fun testSelectClause2() = runBlocking {
    val channels = listOf(Channel<Int>(), Channel<Int>())
    println(channels)
    //生产者
    launch(Dispatchers.IO) {
        select<Unit> {
            launch {
                delay(100)
                channels[0].onSend(666){sendChannel ->
                    println("sent on $sendChannel")
                }
            }

            launch {
                delay(50)
                channels[1].onSend(888){sendChannel ->
                    println("sent on $sendChannel")
                }
            }
        }

    }
    //消费者 会接收到最快的那个channel消息
    GlobalScope.launch {
        println(channels[0].receive())
    }
    GlobalScope.launch {
        println(channels[1].receive())
    }
    delay(1000)
}

/**
     * ### 使用Flow实现多路复用
     多数情况下，我们可以通过构造合适的Flow来实现多路复用的效果。
 */


suspend fun CoroutineScope.getName(desc:String)=async{
     delay(1000)
    "lisi $desc"
}


suspend fun CoroutineScope.getAddress(desc:String)=async{
    delay(500)
    "shenzhen $desc"
}

//遍历调用函数并获取值
fun testSelectFlow() = runBlocking {
    //函数-》协程-》Flow-》Flow合并
   val desc="nihao"
    coroutineScope {
        //两个函数
        listOf(::getName,::getAddress)
            .map { function->//遍历调用函数
                function.invoke(desc)
            }.map { deffered->
                flow { emit(deffered.await()) }
            }.merge() //多个flow合并成一个flow
            .collect {str->//末端操作符
                println(str)
            }

    }
}