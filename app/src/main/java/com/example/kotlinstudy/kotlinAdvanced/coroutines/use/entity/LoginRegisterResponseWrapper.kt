package com.example.kotlinstudy.kotlinAdvanced.coroutines.use.entity

/**
 * 包装Bean 登录 注册 成功的Bean

    {
        "data": {
            "admin": false,
            "chapterTops": [],
            "collectIds": [],
            "email": "",
            "icon": "",
            "id": 66720,
            "nickname": "vip",
            "password": "",
            "publicName": "vip",
            "token": "",
            "type": 0,
            "username": "vip"
        },
        "errorCode": 0,
        "errorMsg": ""
        }

    {
    "data": null,
    "errorCode": -1,
    "errorMsg": "账号密码不匹配！"
    }

 */
data class LoginRegisterResponseWrapper<T>(val data: T, val errorCode: Int, val errorMsg: String)