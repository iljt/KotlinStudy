package com.example.coroutines.repository

import com.example.coroutines.api.User
import com.example.coroutines.api.userServiceApi

/**

 * Created  by Administrator on 2022/1/9 21:33

 */
class UserRepository {

    suspend fun getUser(name:String): User {
        return userServiceApi.getUser(name)
    }
}