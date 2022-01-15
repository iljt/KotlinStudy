package com.example.kotlinstudy.kotlinAdvanced.highorderfunction

/**

 * Created  by Administrator on 2021/12/13 00:06

 */
private fun loginServer(userName:String,userPwd:String,responseResult:(Boolean)->Unit){
    if (userName == "xxx" && userPwd == "123456") {
        responseResult(true)
    }else{
        responseResult(false)
    }
}

fun main(){
    loginServer("xxx","123456"){
        if (it) println("最终登录的结果是：成功") else println("最终登录的结果是：失败")
    }
    loginServer("dfe","123456"){
        if (it) println("最终登录的结果是：成功") else println("最终登录的结果是：失败")
    }
}