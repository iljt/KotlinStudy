package com.example.kotlinstudy.kotlinAdvanced.generics.generic.simple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Simple2 {

    // TODO ================= 形变：包括了 协变 与 逆变 与 不变 =========================

    public static void main1(String[] args) {
        // TODO ================= 协变
        // 之所以可以，是因为继承关系
        String str = "";
        Object obj;
        obj = str;

        List<Object> list1;
        List<String> list2 = new ArrayList<String>();
        // list1 = list2; // 因为List<Object> 不是 List<String>的父类

        // list1 = list2 假设是可以的，会有什么问题？
        // list1.add(new Date());  我存入进去的是Date Object类型
        // String str = list2.get(0);  我取出来是 String 就会 出现类型转换不合理

        List<? extends Object> lists1; // 【? extends】 能够  List<Object> 接收  List<String>
        List<String> lists2 = new ArrayList<String>();
        lists1 = lists2;

        // 父类的引用List<父类>     接收    子类的对象List<子类> = 协变
        // ? extends  == out
    }

    interface List1<E> {
        void addAll(List1<E> items);
    }

    void copyAll(List1<Object> to, List1<String> from) {
        // to = from; // 编译不通过： 因为 List1<String> 并不是 List1<Object> 的子类型
    }

    void copyAll2(List1<? extends Object> to, List1<String> from) {
        to = from;
    }


    public static void main(String[] args) {
        // TODO ================= 逆变
        List<Object> list1 = new ArrayList<Object>();
        // List<String> list2 = list1; // List<String>  无法接收 无法接纳  List<Object>
        list1.add(1111);
        List<? super String> list2 = list1; // 【? super 】 能够让我们 List<String>子引用 接收  List<Object>父类型
        list2.add("222");
        System.out.println(Arrays.toString(list2.toArray()));
        // 逆变 in
    }
}

// 总结：
// 如果我们只需要读取泛型数据（生產者），就可以把此泛型声明成协变（? extends T）   KT out  协变
// 如果我们只需要写入泛型数据（消费者），就可以把此泛型声明成逆变（? super T）     KT in   逆变
// PECS原则： Producer Extends Consumer Super  == 协变 与 逆变
