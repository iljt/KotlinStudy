package com.example.coroutines.flowApplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutines.flowApplication.common.Event
import com.example.coroutines.flowApplication.common.LocalEventBus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**

 * Created  by Administrator on 2022/1/20 23:25

 */
class SharedFlowViewModel : ViewModel() {

    private lateinit var job: Job

    fun startRefresh(){
        job=viewModelScope.launch(Dispatchers.IO) {
            while (true){
                LocalEventBus.postEvent(Event(System.currentTimeMillis()))
            }
        }

    }

    fun stoptRefresh(){
        job?.cancel()
    }
}