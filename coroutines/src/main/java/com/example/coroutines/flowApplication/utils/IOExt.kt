package com.example.coroutines.flowApplication.utils

import java.io.InputStream
import java.io.OutputStream

/**

 * Created  by Administrator on 2022/1/18 00:44

 */


/**
 * Copies this stream to the given output stream, returning the number of bytes copied
 *
 * **Note** It is the caller's responsibility to close both of these resources.
 */
 fun InputStream.copysTo(out: OutputStream, bufferSize: Int = DEFAULT_BUFFER_SIZE,progressCallback:(Long)->Unit): Long {
    var bytesCopied: Long = 0
    val buffer = ByteArray(bufferSize)
    var bytes = read(buffer)
    while (bytes >= 0) {
        out.write(buffer, 0, bytes)
        bytesCopied += bytes
        bytes = read(buffer)
        progressCallback(bytesCopied)
    }
    return bytesCopied
}
