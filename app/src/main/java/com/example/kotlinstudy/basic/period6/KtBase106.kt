package com.example.kotlinstudy.basic.period6

open class MyAnyClass(name: String) // 祖宗类 顶级父类

open class PersonClass(name: String) : MyAnyClass(name = name) // 父类

class StudentClass(name: String) : PersonClass(name = name) // 子类

class TeacherClass(name: String) : PersonClass(name = name) // 子类

class DogClass(name: String) // 其他类 另类

class CatClass(name: String) // 其他类 另类

// TODO 106-Kotlin语言的泛型类型约束学习
// T : PersonClass   相当于  Java的 T extends PersonClass
// PersonClass本身 与 PersonClass的所有子类 都可以使用， 其他的类，都不能兼容此泛型
class KtBase106<T : PersonClass> (private val inputTypeValue: T, private val isR: Boolean = true) {

    // 万能对象返回器
    fun getObj() = inputTypeValue.takeIf { isR }
}

fun main() {
    val any = MyAnyClass("xxx")// 祖宗类 顶级父类

    val per = PersonClass("xxx") // 父类

    val stu = StudentClass("xxx") // 子类
    val tea = TeacherClass("xxx") // 子类

    val dog = DogClass("xxx") // 其他类 另类

    /*val r1 = KtBase106(any).getObj() // 报错了，类型限定了
    println(r1)*/

    val r2 = KtBase106(per).getObj()
    println(r2)

    val r3 = KtBase106(stu).getObj()
    println(r3)

    val r4 = KtBase106(tea).getObj()
    println(r4)

    /*val r5 = KtBase106(dog).getObj() // 报错了，类型限定了
    println(r5)*/

    // KtBase106(CatClass("cat 小白")) // 报错了，类型限定了
}