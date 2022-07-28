package com.example.coroutines.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.coroutines.R
import kotlinx.coroutines.*

class MainActivity2 : Activity() {

    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        //挂起与阻塞
        findViewById<TextView>(R.id.btn).also {
            it.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    //挂起 按钮会立马弹起来
                    delay(8000)
                    Log.e("xxx= ","delay end")
                }
                //阻塞 点击按钮直到sleep执行完后才弹起来。
                //sleep(8000)
               // Log.e("xxx= ","sleep end")
            }

        }
    }


}