package com.example.kotlinstudy.thefirstdemo.`object`

/**

 * Created  by Administrator on 2021/5/30 19:26

 */

fun main(){
    val student=Student("a123",5,"zhangsan",12)
    println(student.toString())
    println(student.eat())
    val cellPhone1=CellPhone("huawei",6666.0)
    val cellPhone2=CellPhone("huawei",6666.0)
    println(cellPhone1)
    println("cellPhone1 equal cellPhone2 ${cellPhone1==cellPhone2}")
    Singleton.SingleTonTest()
    dingceng()
    Util.doAction2()
}