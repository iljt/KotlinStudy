package com.example.kotlinstudy.thefirstdemo.generics

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinstudy.R
import com.example.kotlinstudy.day2.SecondActivity
/**
 * Created  by Administrator on 2021/6/6 19:17
 */
class Test1Activity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        //使用
        startActivity<SecondActivity>(this){
            putExtra("param1","data")
            putExtra("param2","666")
        }
        //相当于
        startActivity<SecondActivity>(this, block = {
            putExtra("param1","data")
            putExtra("param2","666")
        })
    }

    //泛型实化的应用 activity类的跳转 并使用高阶函数进行传参
    inline fun <reified T> startActivity(context: Context,block:Intent.()->Unit){
        val intent= Intent(context,T::class.java)
        intent.block()
        context.startActivity(intent)
    }
}