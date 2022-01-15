package com.example.kotlinstudy.basic.period6

/**

 * Created  by Administrator on 2021/12/28 00:16
// TODO 100-Kotlin语言的接口定义
     1.接口里面的所有成员 和 接口本身 都是 public open 的，所以不需要open，这个是接口的特殊
     2.接口不能有主构造，反正就是没有构造
     3.实现类不仅仅要重写接口的函数，也要重写 接口的成员
     4.接口实现代码区域，全部都要增加 override 关键字来修饰
 */

interface IUSB{
    var usbVersionInfo: String // USB版本相关的信息
    var usbInsertDevice: String // USB插入的设备信息

    fun insertUBS() : String
}

class Keyboard(override var usbVersionInfo: String= "v3.0", override var usbInsertDevice: String="鍵盤接入了UBS口") :IUSB{
    override fun insertUBS(): String ="$usbVersionInfo $usbInsertDevice"
}

class Mouse :IUSB{
    override var usbVersionInfo: String="v1.1"
        // 下面的 set get 都会持有 field，现在是你没有给 usbVersionInfo 赋值， 意味着field是没法让set/get持有的
        get() = field
        set(value) {
            field=value
        }
    override var usbInsertDevice: String="鼠标接入了UBS口"
        get() {
            println("你get了[${field}]值出去了")
            return field
        }
        set(value) {
            field=value
            println("@你set了[${value}]值进来了")
        }

    override fun insertUBS(): String {
       return "Mouse: $usbVersionInfo, $usbInsertDevice"
    }

}

fun main() {
    val iusb1 : IUSB = Mouse()
    println(iusb1.insertUBS())
    println()
    val usb2=Keyboard()
    println(usb2.insertUBS())
}