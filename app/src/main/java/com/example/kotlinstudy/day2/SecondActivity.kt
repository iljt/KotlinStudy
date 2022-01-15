package com.example.kotlinstudy.day2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.kotlinstudy.R

/**
 * 字符串模板数组和区间
 */

class SecondActivity : AppCompatActivity() {
    private val tag="SecondActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        //1、字符串模板
        val strs="aaa"
        //aaa拼接bbb
        e("${strs}bbb")
        //打印$ 要加转义符\
        e("1000\$")
        //2、类型转换
        val numString="111"
       // val numString="111s"//型转换报错
        val num=numString.toInt()
        val float=numString.toFloat()
        e("num= $num float= $float")
        //3、字符串比较equals可以用==替代
        e("${strs.equals(numString)}")
        e("${strs == numString}")
        val car1= Car()
        val car2= Car()
        //对象比较也可以用==替代
        e("${car1==car2}")
        //4、空安全 任何对象都可分为空和不可空
        //如果对象可以为空 那么我们在使用的时候必须判断是否为空 或者加!! 否则编译不通过
        val token=token()
        if (token != null) {
            e("token= ${token.length}")
         }
        //如果token为null 报空指针异常  token为"" 打印token=0
       // e("token= ${token!!.length}")
        //如果token为null token后面加? 表示如果为null打印null 不为空打印token.length
        e("token= ${token?.length}")
        //如果对象不可为空 那么我们可以直接使用
        e("token2= ${token2().length}")
        //5、数组
        val intArrs= intArrayOf(1,2,3,4,5)
        e("intArrs[0]= ${intArrs[0]}")
      //  floatArrayOf()
       // doubleArrayOf()
        var strArrs= arrayOf("str1","str2","str")
        e("strArrs[0]= ${strArrs[0]}")
        //遍历
        for (str in strArrs){
            e(str)
        }
        //角标的方式遍历1
        for (i in strArrs.indices){
            e("strArrs[${i}]= ${strArrs[i]}")
        }
        //角标的方式遍历2
        for (i in IntRange(0,strArrs.size-1)){
          //  e("strArrs[${i}]= ${strArrs[i]}")
        }
        //角标的方式遍历3
        for (i in 0 until strArrs.size){
           // e("strArrs[${i}]= ${strArrs[i]}")
        }
        //区间
        val intRange=IntRange(0,strArrs.size) //[0,3] 0,1,2,3
        val intRange1=0..strArrs.size//[0,3] 0,1,2,3
        val intRange2=0 until strArrs.size//[0,3) 0,1,2

        for (i in intRange){
            e("i= $i")
        }
        for (i in intRange1){
          //  e("i= $i")
        }
        for (i in intRange2){
           // e("i= $i")
        }
        //lambda再探
        // 需求：过滤掉 strArrs 中的 "" 空字符串
        // 步骤：1. 创建一个新的集合用来存放空字符串数组，2. 遍历数组判断字符串是否为空，3. 遍历数组        // RxJava(RxLogin , RxPay) or java8函数式 or lambda
        var strArrsFilter= arrayOf("str3","str4","str5","","")
        strArrsFilter.filter {
            it.isNotEmpty()
        }.forEach { e(it) }

        // when表达式 java switch case
        //默认会加break跳出去 所以没打印 nums in 1..3
        val nums=1
        when(nums){
            1->e("nums is 1")
            2->e("nums is 2")
            in 1..3->("nums in 1..3")
        }
        //可以作为返回值 但是要带else
        val returnStr=when(nums){
            1->"1"
            2->"2"
            else -> ""
        }
        e("returnStr= ${returnStr}")
    }

    private fun e(message:String){
        Log.e(tag,message)
    }

    //?代表返回值可以为空对象
    private  fun token():String?{
        return null
    }
    //不为空
    private  fun token2():String{
        return "ffef"
    }
}