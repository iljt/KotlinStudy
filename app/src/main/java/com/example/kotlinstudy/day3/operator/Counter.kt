package com.example.kotlinstudy.day3.operator

/*
 class Counter( val dayIndex:Int){

    //运算符重载 +
    operator fun plus(counter: Counter):Counter{
        return Counter(dayIndex+counter.dayIndex)
    }

    //运算符重载 -
    operator fun minus(counter: Counter):Counter{
        return Counter(dayIndex-counter.dayIndex)
    }

}*/
//data class就是一个类中只包含一些数据字段
data class Counter(val dayIndex: Int){
      // 操作符重载 +
     operator fun plus(counter: Counter):Counter{
        return Counter(dayIndex+counter.dayIndex)
     }
      // 操作符重载 -
     operator fun minus(counter: Counter):Counter{
        return Counter(dayIndex-counter.dayIndex)
     }
}
