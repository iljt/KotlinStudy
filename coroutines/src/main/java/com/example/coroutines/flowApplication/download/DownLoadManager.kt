package com.example.coroutines.flowApplication.download

import com.example.coroutines.flowApplication.utils.copysTo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.io.IOException

/**
 * Created  by Administrator on 2022/1/18 00:20
 */
object DownLoadManager {

    fun  downLoad(url:String,file:File) : Flow<DownLoadState> {
        return flow{
            val request=Request.Builder().url(url).get().build()
            val reponse=OkHttpClient.Builder().build().newCall(request).execute()
            if(reponse.isSuccessful){
                val scope= CoroutineScope(SupervisorJob())
                reponse.body()?.let { responseBody ->
                   val total=responseBody.contentLength()
                    //文件读写
                    file.outputStream().use { output ->
                        val input=responseBody.byteStream()
                        var emittedProgress=0L

                        input.copysTo(output){bytesCopied ->
                            val progress= bytesCopied*100 / total
                            if ((progress-emittedProgress)>5){
                                scope.launch {
                                    delay(100)
                                    emit(DownLoadState.Progress(progress.toInt()))
                                    emittedProgress=progress
                                 }
                                }

                        }

                    }
                }
                scope.cancel()
                emit(DownLoadState.DownLoadSuccess(file))
            }else{
                throw IOException(reponse.toString())
            }
        }.catch{
            file.delete()
            emit(DownLoadState.Error(it))
        }.flowOn(Dispatchers.IO)
    }
}