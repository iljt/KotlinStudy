package com.example.kotlinstudy.basic.period4

/**

 * Created  by Administrator on 2021/12/26 17:41

 */
data class Person(var name:String,var age:Int)


fun main() {
    var personList:MutableList<Person> = mutableListOf()
    personList.add(Person("Jim", 12))
    personList.add(Person("A-Lin", 12))
    personList.add(Person("Jack", 11))
    personList.add(Person("Tom", 11))
    personList.add(Person("Mary", 14))

    //构造personlist...
    println("----排序前----")
    personList.forEach(::println)

    println("----按age升序排序后----")
    //按年龄升序排序
    personList.sortBy {
        it.age
    }
    //或者写成 //personList.sortBy(Person::age)
    personList.forEach{
        println(it)
    }

    println("----按age降序排序后----")
    //按年龄降序排序
    personList.sortByDescending {
        it.age
    }
    personList.forEach{
        println(it)
    }

    println("----根据多个条件来排序,先根据age降序排列，若age相同，根据name升序排列----")
    //使用 sortWith
    //先根据age降序排列，若age相同，根据name升序排列
    personList.sortWith(comparator = compareBy({-it.age},{it.name}))

    personList.forEach(::println)

}

