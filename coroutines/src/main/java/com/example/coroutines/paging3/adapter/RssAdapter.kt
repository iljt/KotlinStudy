package com.example.coroutines.paging3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.coroutines.databinding.SubscribeRssItemBinding
import com.example.coroutines.paging3.entity.Record

/**

 * Created  by Administrator on 2022/1/22 23:38

 */
class RssAdapter(private val context: Context) : PagingDataAdapter<Record, BindingViewHolder>(object :
    DiffUtil.ItemCallback<Record>() {

    override fun areItemsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem.rssId == newItem.rssId
    }

    override fun areContentsTheSame(oldItem: Record, newItem: Record): Boolean {
        return oldItem == newItem
    }
}) {
    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
       var record=getItem(position)
        record?.let {
           val  binding=holder.binding as SubscribeRssItemBinding
            //Glide设置图片圆角角度
            val roundedCorners =  RoundedCorners(80);
            //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
            // RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(20, 20);
            val options = RequestOptions.bitmapTransform(roundedCorners);
            Glide.with(context).load(it.icon).apply(options).into(binding.imgIv)
            binding.describeTv.text=it.rssBrief
            binding.titleTv.text=it.rssName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding=SubscribeRssItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return  BindingViewHolder(binding)
    }
}