package com.example.kotlinstudy.basic.period6.singleton;

// 1.饿汉式的实现  Java版本
public class SingletonDemo1 {

    private static SingletonDemo1 instance =  new SingletonDemo1();

    private SingletonDemo1() {}

    public static SingletonDemo1 getInstance() {
        return instance;
    }
}
