package com.example.kotlinstudy.thefirstdemo.standard

import android.content.Context
import android.widget.Toast
import com.example.kotlinstudy.R
import java.time.Duration

/**

 * Created  by Administrator on 2021/6/9 21:03

 */

fun main(){
    // "xxx".showToast(context,Toast.LENGTH_LONG)
    //R.string.app_name.showToast(context ,Toast.LENGTH_LONG)
}
//调用方式
//// "xxx".showToast(context,Toast.LENGTH_LONG)
fun String.showToast(context:Context,duration: Int=Toast.LENGTH_SHORT){
    Toast.makeText(context,this,duration).show()
}

//调用方式
//R.string.app_name.showToast(context ,Toast.LENGTH_LONG)
fun Int.showToast(context:Context,duration: Int=Toast.LENGTH_SHORT){
    Toast.makeText(context,this,duration).show()
}