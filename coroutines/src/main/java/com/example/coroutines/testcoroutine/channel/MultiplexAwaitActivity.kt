package com.example.coroutines.testcoroutine.channel

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.R
import com.example.coroutines.api.Response
import com.example.coroutines.api.User
import com.example.coroutines.api.userServiceApi
import com.example.coroutines.viewmodel.UserViewModel
import com.google.gson.Gson
import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select
import java.io.File

/**
     *### 复用多个await
     两个API分别从网络和本地缓存获取数据，期望哪个先返回就先用那个做展示。
 */
class MultiplexAwaitActivity : AppCompatActivity(){
    //异常的捕获
    private val exceptHandler= CoroutineExceptionHandler { _, throwable ->
        println("Caugth: $throwable")
    }
   private val userViewModel:UserViewModel by viewModels()
    private val cachePath="e://cache.txt"
    private val  gson=Gson()

    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_multiplex_await)
       var btn= findViewById<Button>(R.id.btn)
          //创建协程
        btn.also {
            it.setOnClickListener {
                lifecycleScope.launchWhenCreated {
                    val localData=getUserFromLocal("xxx")
                    val netData=getUserFromNet("yyy")
                    val userResponse= select<Response<User>> {
                        //onAwait拿到返回值
                        localData.onAwait{ Response(it,true) }
                     //   netData.onAwait{ Response(it,false) }
                    }

                    userResponse.value?.let { Log.e("value=" ,it.toString()) }
                }
            }
        }
        userViewModel.user.observe(this,{
            btn.text=it?.address
        })
    }


    //拿到本地缓存数据
    suspend fun CoroutineScope.getUserFromLocal(name:String)=async{
        delay(1000)
        File(cachePath).readText().let { gson.fromJson(it,User::class.java) }
    }


    //拿到网络请求数据
    suspend fun CoroutineScope.getUserFromNet(name:String)=async{
        userServiceApi.getUser(name)
    }

}



