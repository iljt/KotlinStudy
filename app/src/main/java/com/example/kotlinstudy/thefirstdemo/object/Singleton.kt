package com.example.kotlinstudy.thefirstdemo.`object`

/**

 * Created  by Administrator on 2021/5/30 20:13

 */
//object 单例类关键字 单例类会将整个类中的所有方法都变成类似于静态方法的调用方式
//如果我们希望只是将类中的某一个方法变成静态方法 使用companion object伴生对象
object Singleton {

    fun SingleTonTest(){
        println("SingleTonTest is called")
    }
}