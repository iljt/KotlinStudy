package com.example.coroutines.flowApplication.common

import kotlinx.coroutines.flow.MutableSharedFlow


/**

 * Created  by Administrator on 2022/1/20 23:20

 */
object LocalEventBus {
     val events = MutableSharedFlow<Event>()

    suspend fun postEvent(event:Event){
        events.emit(event)
    }

}

data class Event(val timestamp:Long)