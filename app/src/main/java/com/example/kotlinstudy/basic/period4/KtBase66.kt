package com.example.kotlinstudy.basic.period4

// TODO 66.Kotlin语言的Map的创建
fun main() {
    val mMap1 : Map<String, Double> = mapOf<String, Double>("xxx" to(534.4), "zzz" to 454.5)
    val mMap2 = mapOf(Pair("xxx", 545.4), Pair("zzz", 664.4))
    // 上面两种方式是等价的哦
    val map3: MutableMap<String, Any> = mutableMapOf("key1" to "aaa",Pair("key2","bbb"),"key3" to 333)
    map3.forEach(action = {
       "key=${it.key} value=${it.value}"
    })
}