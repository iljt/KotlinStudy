package com.example.kotlinstudy.day3

import com.example.kotlinstudy.day3.`interface`.Callback


 class  HttpUtils{
     //默认参数 有初始值 调用的时候可以不传值  没传的值使用默认值
    fun get(callback: Callback,url:String="https://www.baidu.com",cache:Boolean=false) {
        callback.onSuccess("成功")
    }
     //默认参数 没有初始值 调用的时候传值url="xxx" 或者cache=true
     fun get1(callback: Callback,url:String,cache:Boolean) {
         callback.onSuccess("成功")
     }


     // 静态伴生对象 ，只有一份 相当于 java 的 static
     companion object{
         // 相当于静态属性
         const val  baseurl="https://www.baidu.com"
         // vararg 可变参数
         fun sum(vararg nums:Int):Int{
             var sum=0
             //方式一
             /* for (num in nums){
                  sum+=num
              }*/
             //方式二 lambda
             nums.forEach { sum+=it }
             return sum
         }
     }


}