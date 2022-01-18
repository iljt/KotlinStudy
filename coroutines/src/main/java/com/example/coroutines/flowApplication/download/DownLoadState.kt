package com.example.coroutines.flowApplication.download

import java.io.File

/**

 * Created  by Administrator on 2022/1/18 00:21
    sealed 密封类 的成员都是它的子类
    管理下载过程中的一些状态及数据
 */
sealed class DownLoadState{
    object None: DownLoadState()
    data class  Progress(val progress:Int): DownLoadState()
    data class  Error(val throwable: Throwable): DownLoadState()
    data class  DownLoadSuccess(val file: File): DownLoadState()
}
