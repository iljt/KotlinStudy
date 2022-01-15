package com.example.kotlinstudy.day3.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View

//自定义QQStepView继承view
class QQStepView: View{

    constructor(context:Context):this(context,null)

    constructor(context:Context,attrs: AttributeSet?):this(context,attrs,0)

    constructor(context:Context,attrs: AttributeSet?,defStyleAttr:Int):super(context,attrs,defStyleAttr){
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("QQStepView= ","onDraw")
    }
}

