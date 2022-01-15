package com.example.kotlinstudy.kotlinAdvanced.highorderfunction

import java.util.function.Function

/**

 * Created  by Administrator on 2021/12/13 00:06
   高阶函数+扩展函数
 */

// t.()的this,和(t)的it有什么区别?
// T.()  {持有this == T本身}
// (T)  {持有it == T本身}
// (Double) {持有it == Double本身}
// 你对xx扩展  this==xx本身
// T.()    this == T本身 ----> lambda { 持有this }
// (T)     it == T本身 ----> lambda { 持有it }
fun main(){
   val i: Int= 666.MyApply{
      println(it)//it 为T.(Int)的(Int)
      println(this) // this == T本身 == 666
      this
   }
   println(i)
   val s : String="12".MyApply{
      // this == T本身 == "12"
      println(it)
      println(this)
     this
   }
   println(s)
   val d : Double=1.2.MyApply{
      println(it)
      println(this)
      this
   }
   println(d)
   val b : Boolean=false.MyApply{
      println(it)
      println(this)
      this
   }
   println(b)

   "xxx".run {
      length // 已经等到xxx字符串长度了
   }

   "xxx".let {
      it.length // 已经等到xxx字符串长度了
   }
}


// 给整个项目用，来一个扩展函数 默认：public static
// 想给所有类型用， String Char Boolean ... 泛型
//仿写apply
fun <T> T.MyApply(t:T.(T)->T)=t(this)




