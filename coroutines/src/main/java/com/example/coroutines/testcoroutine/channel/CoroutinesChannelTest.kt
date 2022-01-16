package com.example.coroutines.testcoroutine.channel

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

/**
 * Channel
 */

fun main() {
    // testChannel()
    //testChannelCapital()
    //testChannelIterator()
    //testProducerChannel()
    //testActorChannel()
  //  testCloseChannel()
    testBrocastChannel()
}

//使用Channel进行协程通信
fun testChannel()= runBlocking {
    val channel=Channel<Int>()
    //生产者
    val producer=GlobalScope.launch {
        var i=0
        while (true){
            delay(1000)
            channel.send(++i)
            println("send: $i")
        }
    }
   //消费者
    val consumer=GlobalScope.launch {
        while (true){
            val element=channel.receive()
            println("receive: $element")
        }
    }
    joinAll(producer,consumer)
}


//Channel的容量
//Channel实际上就是一个队列，队列中一定存在缓冲区，那么一旦这个缓冲区满了，
// 并且也一直没有人调用receive函数取走缓冲区的数据，send函数就需要挂起。
// 故意让接收端的节奏放慢，发现send总是会挂起，知道receive调用之后才会继续往下执行。
//1s发送一个 3s消费一个 没消费完时channel.send(++i)会挂起，必须等消费完才发下一个
fun testChannelCapital()= runBlocking {
    val channel=Channel<Int>()
    //生产者
    val producer=GlobalScope.launch {
        var i=0
        while (true){
            channel.send(++i)
            println("send: $i")
        }
    }
    //消费者
    val consumer=GlobalScope.launch {
        while (true){
            delay(3000)
            val element=channel.receive()
            println("receive: $element")
        }
    }
    joinAll(producer,consumer)
}

/**
    ### 迭代Channel
    Channel本身确实像序列，所以我们在读取的时候可以直接获取一个Channel的iterator。
   //一次性发，每隔2500ms一个一个接收
 */
fun testChannelIterator()= runBlocking {
    val channel=Channel<Int>(Channel.UNLIMITED)
    //生产者
    val producer=GlobalScope.launch {
       for (i in 1..6){
           channel.send(i * i)
           println("send: ${i * i}")
       }
    }
    //消费者
    val consumer=GlobalScope.launch {
        //方式1
        /*val iterator=channel.iterator()
         while (iterator.hasNext()){
             val element=iterator.next()
             println("receive: $element")
             delay(2500)
         }*/
        //方式2
        for (element in channel){
            println("receive: $element")
            delay(2500)
        }
    }
    joinAll(producer,consumer)
}

/**
    ### produce与actor
    1、构造生产者与消费者的便捷方法。
    2、我们可以通过produce方法启动一个生产者协程，并返回一个ReceiveChannel，其他协程就可以用这个Channel来接收数据了，反过来，我们可以用actor来启动一个消费者协程。
 */
fun testProducerChannel()= runBlocking {
    val receiveChannel:ReceiveChannel<Int> = GlobalScope.produce {
        repeat(10){
            delay(2000)
            send(it)
            println("send: $it")
        }
    }
    //消费者
    val consumer=GlobalScope.launch {
        for (element in receiveChannel){
            println("receive: $element")
        }
    }
    consumer.join()
}

/**
### produce与actor
1、构造生产者与消费者的便捷方法。
2、我们可以通过produce方法启动一个生产者协程，并返回一个ReceiveChannel，其他协程就可以用这个Channel来接收数据了，反过来，我们可以用actor来启动一个消费者协程。
 */
fun testActorChannel()= runBlocking {
    val sendChannel:SendChannel<Int> = GlobalScope.actor<Int> {
        while (true){
            val element= receive()
            println("receive: $element")
            //  delay(3000)
        }
    }
    //消费者
    val producer=GlobalScope.launch {
        for (i in 1..10 ){
            sendChannel.send(i)
            println("send: $i")
            //  delay(1000)
        }
    }
    producer.join()
}


/**
    ### Channel的关闭
    1、produce和actor返回的Channel都会随着对应的协程执行完毕而关闭，也正是这样，Channel才被称位热数据流。
    2、对于一个Channel，如果我们调用了他的close方法，它会立即停止接收新元素，也就是说这时它的isClosedForSend会立即返回true。而由于Channel缓冲区的存在，这时候可能还有一些元素没有被处理完，因此要等所有元素都被读取完之后isClosedForReceive才会返回true。
    3、Channel的生命周期最好由主导方来维护，建议由主导一方实现关闭。
 */
fun testCloseChannel()= runBlocking {

    val channel=Channel<Int>(3)
    //生产者
    val producer=GlobalScope.launch {
       List(3){ i->
           channel.send(i)
           println("send: $i")
        }
        channel.close()
        println("""close channel.
           -- closeForSend=${channel.isClosedForSend}--
           -- isClosedForReceive=${channel.isClosedForReceive}--
            """.trimIndent())
    }
    //消费者
    val consumer=GlobalScope.launch {
        for (element in channel){
            println("receive: $element")
            delay(1000)
        }
        println("""After consuming.
           -- closeForSend=${channel.isClosedForSend}--
           -- isClosedForReceive=${channel.isClosedForReceive}--
            """.trimIndent())
    }
    joinAll(producer,consumer)
}


/**
    ### BroadcastChannel
    前面提到，发送端和接收端在Channel中存在一对多的情形，从数据处理本身来讲，虽然有多个接收端，但是同一个元素只会被一个接收端读到。
    广播则不然，多个接收端不存在互斥行为。
 */
fun testBrocastChannel()= runBlocking {

   // val brocastChannel= BroadcastChannel<Int>(Channel.BUFFERED)
    val channel= Channel<Int>()
    val brocastChannel=channel.broadcast(3)
    //生产者
    val producer=GlobalScope.launch {
        List(3){ i->
            delay(100)
            println("send: $i")
            brocastChannel.send(i)

        }
        brocastChannel.close()
    }
    //消费者
    List(3){ index->
         GlobalScope.launch {
            val receiveChannel=brocastChannel.openSubscription()
            for (element in receiveChannel){
                println("[#$index] receive: $element")
            }
         }
    }.joinAll()
}