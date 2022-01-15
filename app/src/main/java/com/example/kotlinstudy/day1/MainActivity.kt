package com.example.kotlinstudy.day1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.kotlinstudy.R
import com.example.kotlinstudy.day2.SecondActivity
import com.example.kotlinstudy.day3.ThirdActivity
import com.example.kotlinstudy.thefirstdemo.highorderfunction.open
import com.example.kotlinstudy.thefirstdemo.lazy.later
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 基本数据类型和函数定义
 */
const val tag1="MainActivity" //const val ：相当于 Java中的final ，编译时常量

class MainActivity : AppCompatActivity() {
    private var tag="MainActivity"
    //验证later实现懒加载
    val p by lazy {
        Log.e("TAG","run codes on later block")
        "test later"
    }
    val p1 by later {
        Log.e("TAG","run codes on later block1")
        "test later1"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        kotlin_tv.text = "字符串模板数组和区间"

        kotlin_tv.setOnClickListener {
            val intent=Intent(MainActivity@this,
                SecondActivity::class.java)
            startActivity(intent)
        }
        kotlin_tv2.setOnClickListener {
            val intent=Intent(MainActivity@this,
                ThirdActivity::class.java)
            startActivity(intent)
        }
        //基本数据类型
        //定义 var/val 变量名：类型=xxx
  /*      val num:Int=11
        val str:String="str"
        val bool:Boolean=true
        val double:Double=2.2
        var c:Char='C'
        var float:Float=2.2f*/
        //自动推倒类型
        //val 定义的变量不可修改 var定义的变量可修改
        val num=11
        val str="str"
        val bool=true
        val double=2.2
        var c='C'
        var float=2.2f
        //java 里面的 final ：不可修改，编译的时候会把引用的值进行 copy
     //   const val ：相当于 Java中的final ，编译时常量

      //  tag="TAG"
       // Log.e(TAG,"num= "+num+" str= "+str+" bool= "+bool+" double= "+double+" c= "+c+" float= "+float)
        //字符串模板
        Log.e(tag, "num= $num str= $str bool= $bool double= $double c= $c float= $float")
        Log.e(tag1, "num= $num str= $str bool= $bool double= $double c= $c float= $float")
        //函数
        val sum=add(1,2)
        val sum1=add1(1,2)

        e("sum= $sum sum1= $sum1")


        val sum2=minus(2,1)
        val sum3=minus1(2,1)
        e("sum2= $sum2 sum3= $sum3")
        //调用SharedPreferences.kt中的高阶函数
        getSharedPreferences("data", Context.MODE_PRIVATE).open {
            putString("name","tom")
            putInt("age",28)
            putBoolean("marrried",false)
        }

    }

    //函数定义 fun 函数名称（参数名1：参数类型）: 返回值
    //Unit为null返回值 可以省略
    //打印log的函数
    fun e(message:String){
        Log.e(tag,message)
    }

    //两个数相加
    fun add(num1:Int,num2:Int): Int {
        return num1+num2
    }
   // 简化上面函数
   fun add1(num1:Int,num2:Int)=num1+num2

    //在kotlin里面函数可以作为参数传递
    val minus=fun(num1:Int,num2:Int):Int{
         return num1-num2
    }

    //lambda初探
    //Lambda表达一般使用“{ }”包围。
    //表达式：函数入口参数 -> 返回值
    val minus1={num1:Int,num2:Int -> num1-num2}

    //多次点击日志不会多次打印 代码块里面的代码只会执行一次
    fun click(view: View) {
        var str=p
      //  var str1=p1//报错 只是实现了懒加载 null没处理 使用lazy函数可以
       Log.e("TAG","$str")
    }



}

