package com.example.coroutines.flowApplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coroutines.flowApplication.entity.Article
import com.example.coroutines.flowApplication.net.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
/**

 * Created  by Administrator on 2022/1/18 21:49

 */
class ArticleViewModel(application: Application) : AndroidViewModel(application) {
    val articles = MutableLiveData<List<Article>>()

    fun searchArticle(key:String) {
        viewModelScope.launch {
            flow {
                val list= RetrofitClient.articleApi.searchArticle(key)
                emit(list)
            } .flowOn(Dispatchers.IO)
                .catch { e->
                    Log.e("e= ",  e.message.toString())
                    e.printStackTrace()
                }
                .collect {
                    articles.value=it
                }
        }

    }


}