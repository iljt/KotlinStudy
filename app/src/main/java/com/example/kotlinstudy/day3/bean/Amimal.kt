package com.example.kotlinstudy.day3.bean

//// 创建类 Dog  相当于 Java 类，并且定义了两个属性 name age ,而且定义了两个(get)方法 getAge getName

/*//  方式1
//class Dog(val name:String,val age :Int)
//如果是var才会提供set方法
class Dog(var name: String?, var age:Int)
//构造方法重载
{
    //this(name,0)调用默认构造函数
    constructor(name:String):this(name,0)
    //无参数构造函数
    constructor():this(null,0)
}*/

//  方式2 和java类似
open class Amimal{
    // 定义 name 属性，默认的情况就会有 get 和 set 方法
    // 默认属性前面有 protected ，如果想把get私有 那么可以把属性设置为私有private
     var name:String?=null  //加private修饰符 那么name不能赋值和取值
   // private set//私有掉name 的set方法 这样name不能赋值
   // private get//私有掉name 的get方法 这样name不能取值

    var age:Int=0
    //如果age小于0 则赋值0  复写get方法
    get() {
        return  if(field<0) 0 else field
    }

    //构造方法重载
    constructor()

    //构造方法重载
    constructor(name:String){
        this.name=name
    }

    //构造方法重载
    constructor(name:String,age:Int)  {
        this.name=name
        this.age=age
    }


}

