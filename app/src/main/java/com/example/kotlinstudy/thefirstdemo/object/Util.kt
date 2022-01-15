package com.example.kotlinstudy.thefirstdemo.`object`

/**

 * Created  by Administrator on 2021/5/31 23:42

 */
class Util {

    fun doAction1(){
        println("do action1")
    }

    //Kotlin会保证Util类始终只会存在一个伴生类对象 所以调用Util.doAction2()实际上就是调用了Util伴生类对象的doAction2()方法
    companion object{
        //@JvmStatic 只能加载 companion object或者单列类Object上 Kotlin将该注解标记的类编译成真正的静态方法 这样我们Java代码才能使用Util.doAction2()调用该方法 否则只能在Kotlin中调用
        //我们还有一种方式把方法变为静态方法 使用顶层方法 我们新建一个kotlin文件时类型选择File里面的方法就是顶层方法
        @JvmStatic
        fun doAction2(){
            println("do action2")
        }

    }

}