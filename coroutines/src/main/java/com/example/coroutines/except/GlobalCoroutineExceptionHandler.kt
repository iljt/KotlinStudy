package com.example.coroutines.except

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 * Created  by Administrator on 2022/1/15 09:03
 * 全局异常处理器可以获取到所有协程未处理的未捕获的异常，不过它并不能对异常进行捕获，它不能阻止程序崩溃
 */
class GlobalCoroutineExceptionHandler :CoroutineExceptionHandler {

    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler

    override fun handleException(context: CoroutineContext, exception: Throwable) {
      Log.e("globalCoroutineExcept= ","Unhandled Coroutine Exception: $exception")
     }
}