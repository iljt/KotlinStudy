package com.example.coroutines.flowApplication.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
/**
 * ### StateFlow
    Flow是冷流,那么什么是冷流？简单来说，如果Flow有了订阅者Collector之后，发射出来的值才会实实在在的存在于内存之中，跟懒加载很像。
    与之相对应的是热流，StateFlow 和 SharedFlow是热流，在垃圾回收之前，都是存在内存之中，并且处于活跃状态的。
    StateFlow是一个状态容器式可观察数据流，可以向其收集器发出当前状态更新和新状态跟新。还可以通过其value属性读取当前状态值。
 */

class NumberViewModel : ViewModel() {
    val number = MutableStateFlow(0)

    fun increment(){
        number.value++
    }

    fun decrement(){
        number.value--
    }


}