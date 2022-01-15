package com.example.coroutines.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Button
import com.example.coroutines.R
import com.example.coroutines.api.userServiceApi
import kotlinx.coroutines.*

/**
 *MainScope
 * 在Activity中使用，可以在onDestroy()中使用。
 *
 */
class MainActivity4 : Activity(),CoroutineScope by MainScope(){
    //,CoroutineScope by MainScope()使用委托的方式
  //private val mainScope= MainScope()

    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
       var btn= findViewById<Button>(R.id.btn)
          //创建协程
        btn.also {
            it.setOnClickListener {
              /*  mainScope.launch {
                    val  user=userServiceApi.getUser("xxx")
                    btn.text=user?.address*/
                   /* try {
                        //怎么证明协程取消了？
                        //取消协程后，则mainScope里面的所有任务都会被取消，delay挂起函数也会取消，会抛出异常，则协程取消了
                        delay(5000)
                    }catch (e:Exception){
                        e.printStackTrace()
                    }*/

                //使用委托的方式
                launch {
                    val  user= userServiceApi.getUser("xxx")
                    btn.text=user?.address
                }

            }
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        //取消协程，则mainScope里面的所有任务都会被取消
       // mainScope.cancel()
        //使用委托的方式
        cancel()
    }
}