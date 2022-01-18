package com.example.coroutines.flowApplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.flowApplication.db.AppDatabase
import com.example.coroutines.flowApplication.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**

 * Created  by Administrator on 2022/1/18 21:49

 */
class UserViewModel(application: Application) : AndroidViewModel(application) {

    fun insertUser(userId:String,firstName:String,lastName:String){
        viewModelScope.launch {
            AppDatabase.getInstance(getApplication())
                .userDao()
                .insertUser(User(userId.toInt(),firstName,lastName))
            Log.e("xxx","insert user: $userId")
        }
    }

    fun deleteUser(userId:String){
        viewModelScope.launch {
            AppDatabase.getInstance(getApplication())
                .userDao()
                .deleteUser(userId)
            Log.e("xxx","delete user: $userId")
        }
    }

    fun getAllUser(): Flow<List<User>>{
       return AppDatabase.getInstance(getApplication())
            .userDao()
            .getAllUser().catch { e->
                e.printStackTrace()
            }.flowOn(Dispatchers.IO)
    }
}