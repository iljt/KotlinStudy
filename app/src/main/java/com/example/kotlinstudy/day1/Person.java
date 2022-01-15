package com.example.kotlinstudy.day1;

import android.util.Log;
import android.view.View;

public class Person {
    //java 里面的 final ：不可修改，编译的时候会把引用的值进行copy build一下
    // 编译后路径build\intermediates\javac\debug\compileDebugJavaWithJavac\classes\com\example\kotlinstudy\Person.class
    //里面 private String name1 = "xxxx"; 编译的时候会把引用的值xxxx进行copy给了name1 所以name1 = "xxxx"
    private  final String name="xxxx";
    private String name1=name;

   //lambda 在java使用
    public void testLambda(View v){
        v.setOnClickListener(view->{
            Log.e("xxx","方式1");
        });
        v.setOnClickListener(view->Log.e("xxx","方式1"));


    }

}
