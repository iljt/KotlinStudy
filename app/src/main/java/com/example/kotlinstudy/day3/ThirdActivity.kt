package com.example.kotlinstudy.day3

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinstudy.day3.bean.Outer
import com.example.kotlinstudy.R
import com.example.kotlinstudy.day3.`interface`.HttpCallback
import com.example.kotlinstudy.day3.bean.Amimal
import com.example.kotlinstudy.day3.bean.Dog
import com.example.kotlinstudy.day3.operator.Counter
import java.io.IOException
import java.lang.StringBuilder

/**
 *  抽象类、接口和面向对象
 */

class ThirdActivity: AppCompatActivity(){
   private val tag="ThirdActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
       // val dog=Dog("xiaobai",3)
        val amimal= Amimal("fsewr")
      //  dog.name="22"
        amimal.age=-4
        Log.e(tag,"name= ${amimal.name} age= ${amimal.age}")

        //继承
        val dog= Dog("xiaobai")
        dog.name="xxxx"
        dog.age=1
        Log.e(tag,"name= ${dog.name} age= ${dog.age}")

        //抽象类与匿名内部类object:xxx
        val httpUtils= HttpUtils()
        httpUtils.get(object:HttpCallback(){

            override fun onSuccess() {
                Log.e(tag,"onSuccess")
            }

           override fun onError(e: IOException) {

           }
       },cache = true)//默认参数 调用的时候可以不传值 没传的值使用默认值 传值的地方必须告诉是url="xxx"或者cache=true以区分传给了哪个参数

        httpUtils.get1(object:HttpCallback(){

            override fun onSuccess() {
                Log.e(tag,"onSuccess 1")
            }

            override fun onError(e: IOException) {

            }
        },"https://www.baidu.com",true)
        //伴生对象
        val sum=HttpUtils.sum(1,2,3,4,5)
        Log.e(tag,"sum= ${sum}  baseurl=${HttpUtils.baseurl}")
        //运算符重载
        var counter1=Counter(33)
        var counter2=Counter(22)
        val counter=counter1+counter2
        val minusCounter=counter1-counter2

        Log.e(tag,"plus= ${counter.dayIndex}  minus= ${minusCounter.dayIndex}")

        //类的方法扩展(扩展函数)
        val  arrs= arrayOf(1,2,3)
       // Array的扩展函数
        arrs.isNotEmpty()
        // 对String类方法进行扩展       效果 // abc.mulit(3) = abc*abc*abc
        var str="abc"
        val strMulit=str.mulit(3)
        val strMulitOperator=str*2
        Log.e(tag,"strMulit= $strMulit  strMulitOperator= $strMulitOperator")
    }

    //类的方法扩展(扩展函数)
    fun  String.mulit(number: Int): String {
        var stringBuilder=StringBuilder()
        for (num in 1..number){
            stringBuilder.append(this)//this代表String
        }
        return stringBuilder.toString()
    }

    //用操作符的方式
    operator fun  String.times(number: Int): String {
        var stringBuilder=StringBuilder()
        for (num in 1..number){
            stringBuilder.append(this)//this代表String
        }
        return  stringBuilder.toString()
    }
    //内部类和枚举
    val inner1 = Outer().Inner()
   // inner1.printName()


}