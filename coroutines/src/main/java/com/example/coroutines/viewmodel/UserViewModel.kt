package com.example.coroutines.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.api.User
import com.example.coroutines.repository.UserRepository
import kotlinx.coroutines.launch

/**

 * Created  by Administrator on 2022/1/9 21:28

 */
class UserViewModel:ViewModel() {
   val user= MutableLiveData<User>()

   private  val  userRepository=UserRepository()

    fun getUser(name:String){
      viewModelScope.launch {
         user.value= userRepository.getUser(name)
      }
   }
}