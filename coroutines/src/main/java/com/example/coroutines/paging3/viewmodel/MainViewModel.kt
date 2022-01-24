package com.example.coroutines.paging3.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.coroutines.paging3.entity.Record
import com.example.coroutines.paging3.repository.Repository

/**

 * Created  by Administrator on 2022/1/23 22:09

 */
class MainViewModel @ViewModelInject constructor(
    private val rssRepository:Repository
) :ViewModel(){

    val data: LiveData<PagingData<Record>> =
        rssRepository
        .fetchRssRecordList()
        .cachedIn(viewModelScope)
            .asLiveData()

}