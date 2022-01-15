package com.example.kotlinstudy.basic.period6.singleton

// 3.懒汉式的实现  KT版本 安全
class SingletonDemo3Kt {

    companion object {

        private  var myInstance :SingletonDemo3Kt?=null
               get() {
                    if (field ==null){
                        field=SingletonDemo3Kt()
                    }
                   return field
                }

        //加上同步锁，保证线程安全
        @Synchronized
        fun  getInstance()= myInstance!!

    }

    fun show() {
        println("show")
    }
}

fun main() {
    SingletonDemo3Kt.getInstance().show()
}