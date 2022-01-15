package com.example.kotlinstudy.thefirstdemo.utils

/**

 * Created  by Administrator on 2021/6/9 20:54

 */

//比较n个数最大值
fun main(){
    val a=3.5
    val b=4.5
    val c=2.5
    val largestNum= max(a,b,c)
    println("$largestNum")

}

//java中规定 所有类型的数字都是可以比较的 因此必须实现Comparable接口 这个规定在Kotlin中同样成立
fun <T:Comparable<T>> max(vararg nums: T):T{
    if(nums.isEmpty()){
        throw RuntimeException("Params can not be empty.")
    }
    var maxNum=nums[0]
    for (num in nums){
        if(num>maxNum){
            maxNum=num
        }
    }
    return maxNum
}