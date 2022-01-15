package com.example.coroutines.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.R
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

/**
 *全局异常处理器可以获取到所有协程未处理的未捕获的异常，不过它并不能对异常进行捕获，虽然不能阻止程序崩溃，全局异常处理器在程序调试的异常上报等场景中仍然有非常大的用处。
  我们需要在classpath下面创建META-INF/services目录，并在其中创建一个kotlinx.coroutines.CoroutineExceptionHandler的文件，文件内容就是我们的全局异常处理器的全类名。
 *
 */
class GloabExceptActivity : AppCompatActivity(){
    //异常的捕获
    //但如果我们使用了exceptHandler捕获异常，则优先使用自己的捕获异常
    private val exceptHandler= CoroutineExceptionHandler { _, throwable ->
        Log.e("捕获到异常","Caugth: $throwable")

    }

    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_global_except)
       var btn= findViewById<Button>(R.id.btn)
          //创建协程
        btn.also {
            it.setOnClickListener {
                //报错
                lifecycleScope.launch/*(exceptHandler)*/ {
                    Log.e("xxx","onclick")
                    //报错
                    "xxx".substring(11)
                }
            }
        }

    }

}