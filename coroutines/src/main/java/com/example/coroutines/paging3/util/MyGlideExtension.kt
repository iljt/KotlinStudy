package com.example.coroutines.paging3.util

import com.bumptech.glide.Priority
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.BaseRequestOptions
import com.example.coroutines.R

/**

 * Created  by Administrator on 2022/1/23 00:13

 */
@GlideExtension
class MyGlideExtension()  {
    @GlideOption
    fun applyAvator(options: BaseRequestOptions<*>, size: Int): BaseRequestOptions<*> {
        return options.placeholder(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .override(size)
            .centerCrop()
    }

    @GlideOption
    fun applyRoundImageByRadius(
        options: BaseRequestOptions<*>,
        radius: Int
    ): BaseRequestOptions<*> {
        return options
            .priority(Priority.HIGH)
            .dontAnimate()
            .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
            .error(R.mipmap.ic_launcher)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true)
            .transforms(CenterCrop(), RoundedCorners(radius))
    }
}