package com.example.coroutines.paging3.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.example.coroutines.databinding.RecycleviewItemLoadmoreBinding

/**

 * Created  by Administrator on 2022/1/23 11:24

 */
class RssLoadMoreAdapter(private val context:Context) : LoadStateAdapter<BindingViewHolder>() {
    override fun onBindViewHolder(holder: BindingViewHolder, loadState: LoadState) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): BindingViewHolder {
        val  binding=RecycleviewItemLoadmoreBinding.inflate(LayoutInflater.from(context),parent,false)
        return BindingViewHolder(binding)
    }
}