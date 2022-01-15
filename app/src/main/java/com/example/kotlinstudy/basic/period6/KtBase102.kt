package com.example.kotlinstudy.basic.period6

/**
 * abstract
 */

abstract class BaseActivity {

    fun onCreate() {
        setContentView(layoutID = getLayoutID())
        initView()
        initData()

    }

    private fun setContentView(layoutID: Int) {
       println("加载{$layoutID}布局xml中")
    }

    abstract fun getLayoutID(): Int
    abstract fun initView()
    abstract fun initData()
}

class MainActivity : BaseActivity() {

    override fun getLayoutID(): Int = 564

    override fun initView() = println("做具体初始化View的实现")

    override fun initData() = println("做具体初始化数据的实现")


    fun show() {
        super.onCreate()
    }
}

class LoginActivity : BaseActivity() {

    override fun getLayoutID(): Int = 564

    override fun initView() = println("做具体初始化View的实现")

    override fun initData() = println("做具体初始化数据的实现")


    fun show() {
        super.onCreate()
    }
}

// TODO 102-Kotlin语言的抽象类学习
fun main() = LoginActivity().show()