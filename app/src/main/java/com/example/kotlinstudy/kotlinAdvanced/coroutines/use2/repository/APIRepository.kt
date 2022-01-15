package com.example.kotlinstudy.kotlinAdvanced.coroutines.use2.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.kotlinstudy.kotlinAdvanced.coroutines.use2.api.APIClient
import com.example.kotlinstudy.kotlinAdvanced.coroutines.use2.api.WanAndroidAPI
import com.example.kotlinstudy.kotlinAdvanced.coroutines.use2.entity.LoginRegisterResponse2
import com.example.kotlinstudy.kotlinAdvanced.coroutines.use2.entity.LoginRegisterResponseWrapper2

class APIRepository {

    suspend fun reqeustLogin(username: String, userpwd: String, stateManager : MutableLiveData<String>)
        : LoginRegisterResponseWrapper2<LoginRegisterResponse2> {

        if (username.isEmpty() || userpwd.isEmpty()) {
            Log.d("xxx", "username.isEmpty() || userpwd.isEmpty()")
        }
        // TODO 可以做很多校验工作的，......
        // TODO ...
        stateManager.value = "你报错了"

        return APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
            .loginActionCoroutine(username, userpwd)
    }

}