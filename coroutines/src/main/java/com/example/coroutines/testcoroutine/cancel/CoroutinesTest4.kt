package com.example.coroutines.testcoroutine.cancel

import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.FileReader

/**###
  1、标准函数use释放资源  该函数只能被实现了Closeable接口的对象使用，程序结束的时候会自动调用close，适合文件对象
 */

fun main() {
    testUserFunction()
}



//协程释放资源之标准函数use
fun testUserFunction() = runBlocking {
  /* var br=BufferedReader(FileReader("D:\\aa.txt"))
    with(br){
        var line:String?
        try {
            while (true){
                line=br.readLine() ?: break
                println(line)
            }
        }finally {
            //常规写法，在这里释放资源
            close()
        }
    }*/

    println()
    BufferedReader(FileReader("D:\\aa.txt")).use {
        //use函数的finally会调用close自动释放资源
        var line:String?
        while (true){
            line=it.readLine() ?: break
            println(line)
        }
    }

}



