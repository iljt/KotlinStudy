// TODO 高阶函数第一版
fun main() {
    loginEngine("xxx2", "123456")
}

// 登录功能 高阶函数完成
private fun loginEngine(userName: String, userPwd: String) : Unit {
    // 使用高阶函数
    loginServer(userName, userPwd) { name, pwd ->
        if (name == "xxx" && pwd == "123456") {
            println("恭喜：${name} 登录成功")
        } else {
            println("登录失败")
        }
    }
}

// 高阶函数 规则
private fun loginServer(userName: String, userPwd: String, responseResult: (String, String) -> Unit) =
    // 做 name pwd 的校验工作
    // ...
    responseResult(userName, userPwd)
