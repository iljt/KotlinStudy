package com.example.kotlinstudy.basic.period4

// TODO 67.Kotlin语言的读取Map的值
// 方式一 [] 找不到会返回null
// 方式二 getOrDefault
// 方式三 getOrElse
// 方式四 与Java一样 会奔溃
fun main() {
    val mMap /*: Map<String, Int>*/ = mapOf("xxx" to 123,"Kevin" to 654,Pair("zzz",545))

    // 方式一 [] 找不到会返回null
    println(mMap["xxx"]) // 背后对[] 运算符重载了
    println(mMap["zzz"])
    println(mMap.get("zzz")) // get 与 [] 完完全全等价的
    println(mMap["XXX"]) // map通过key找 如果找不到返回null，不会奔溃

    println()

    // 方式二 getOrDefault
    println(mMap.getOrDefault("xxx", -1))
    println(mMap.getOrDefault("xxx2", -1))

    // 方式三 getOrElse
    println(mMap.getOrElse("xxx") {-1})
    println(mMap.getOrElse("xxx2") {-1})

    println()

    // 方式四 getValue 与Java一样 会奔溃  尽量不要使用此方式
    println(mMap.getValue("xxx"))
//    println(mMap.getValue("XXX"))

    // map获取内容，尽量使用 方式二 方式三
}