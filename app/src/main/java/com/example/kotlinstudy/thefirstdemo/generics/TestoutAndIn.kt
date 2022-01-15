package com.example.kotlinstudy.thefirstdemo.generics

/**

 * Created  by Administrator on 2021/6/6 23:49
   协变和逆变
   协变定义：如果定义了一个MyClass<T>的泛型类 其中A是B的子类型，而且MyClass<A>是MyClass<B>的子类型 那么称MyClass在T这个泛型上是协变的
   如果一个泛型类在其泛型类型的数据上是只读的话 那么它是没有类型转转的安全隐患的 要实现这一点 要让MyClass<T>类中所有的方法都不能接收T类型的参数，也就是说T只能出现在out位置而不能出现在in位置上
   相当于java里面的<T extends Person> 泛型的上界 不能存数据  生产者  kotlin 关键字out修饰
   逆变定义：如果定义了一个MyClass<T>的泛型类 其中A是B的子类型，而且MyClass<B>是MyClass<A>的子类型 那么称MyClass在T这个泛型上是逆变的
   相当于java里面的<T super Person>   泛型的下界 不能取数据  消费者  kotlin 关键字in修饰
   PECS Producer Extends Consumer Super
 */

//这里出现在参数位置（in）是因为val修饰 所以主构造函数中的泛型T仍然是只读的
//out因为out只读 不需设置set方法
class SimpleData<out T>(val data:T?){
  fun get():T?{
      return data
  }
}


fun main(){
    val student= Student("tom", 11)
    val data=SimpleData<Student>(student)
    //进行协变声明后SimpleData<Student>是SimpleData<Person>的子类型可以传递给handleMyData（）方法
    handleMyData(data)
    val studentData=data.get()
    println("studentData=${studentData.toString()}")

}

fun handleMyData(data:SimpleData<Person>){
    val personData=data.get()
    println("personData=${personData.toString()}")

}

//另外 Kotlin里面的List在泛型E前面加了out关键字，说明List在泛型E上是协变的
// Kotlin里面List<Student>是允许传递给某个接收List<Person>类型的函数的
//java不允许 因为被类型擦除了
