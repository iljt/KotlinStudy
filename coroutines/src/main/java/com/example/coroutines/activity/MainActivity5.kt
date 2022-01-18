package com.example.coroutines.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.R
import com.example.coroutines.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 *在项目中使用协程+Retrofit+ViewModel+LiveData
 *
 */
class MainActivity5 : AppCompatActivity(){
    //异常的捕获
    private val exceptHandler= CoroutineExceptionHandler { _, throwable ->
        println("Caugth: $throwable")
    }

  private val userViewModel:UserViewModel by viewModels()

    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)
       var btn= findViewById<Button>(R.id.btn)
          //创建协程
        btn.also {
            it.setOnClickListener {
                userViewModel.getUser("xxx")
                //报错
                lifecycleScope.launch(exceptHandler) {
                    Log.e("xxx","onclick")
                    //报错
                    "xxx".substring(11)
                }

            }
        }
        userViewModel.user.observe(this,{
            btn.text=it?.address
        })
    }

}