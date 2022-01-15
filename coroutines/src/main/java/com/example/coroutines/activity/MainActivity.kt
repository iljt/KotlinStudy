package com.example.coroutines.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.os.AsyncTask
import android.os.Bundle
import android.widget.TextView
import com.example.coroutines.R
import com.example.coroutines.api.User
import com.example.coroutines.api.userServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : Activity() {

    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //异步任务处理网络请求
        findViewById<TextView>(R.id.asy_tv).setOnClickListener{

            object: AsyncTask<Void, Void, User>() {
                override fun doInBackground(vararg params: Void?): User? {
                 return userServiceApi.loadUser("xxx").execute().body()
                }


                override fun onPostExecute(result: User?) {
                    super.onPostExecute(result)
                    findViewById<TextView>(R.id.asy_tv).text=result?.address
                }

            }.execute()

        }
        //协程处理网络请求 可以用同步的方式写异步的代码，避免异步任务的地狱回调
        findViewById<TextView>(R.id.coroutines_tv).also {
            it.setOnClickListener {
                GlobalScope.launch(Dispatchers.Main) {
                    //协程任务调度器，切换到子线程，也可以不用写，retrofit对协程有单独的支持
                    val user= withContext(Dispatchers.IO){
                      userServiceApi.getUser("xxx")
                    }
                    findViewById<TextView>(R.id.coroutines_tv).text=user?.address
                }
            }

        }
    }
}