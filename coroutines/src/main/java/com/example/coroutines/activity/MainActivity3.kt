package com.example.coroutines.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import com.example.coroutines.R
import kotlin.coroutines.*

/**
 * 利用协程的基础设施层标准创建协程 kotlin.coroutines.*里面的内容
 * 业务框架层 kotlinx.coroutines.xxx里面的内容
 */
class MainActivity3 : Activity() {

    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        //创建一个挂起函数，这个挂起函数就是我们的协程体
        //协程的挂起点通过continuation保存起来了
       val continuation= suspend {
           666
        }.createCoroutine(object :Continuation<Int>{
            override val context: CoroutineContext=EmptyCoroutineContext

            override fun resumeWith(result: Result<Int>) {
              println("Coroutine End: $result" )
            }

        })
        //必须启动协程
        continuation.resume(Unit)


    }
}