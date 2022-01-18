package com.example.coroutines.flowApplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.databinding.ItemUserBinding
import com.example.coroutines.flowApplication.entity.Article
import com.example.coroutines.flowApplication.entity.User
/**
 * Created  by Administrator on 2022/1/18 22:04
 */
class ArticleAdapter(private val context: Context) :RecyclerView.Adapter<BindingViewHolder>() {

    private  val data = ArrayList<Article>()

    fun setData(data: List<Article>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
       val binding=ItemUserBinding.inflate(LayoutInflater.from(context),parent,false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item=data[position]
        val binding=  holder.binding as ItemUserBinding
        binding.userInfo.text="${item.content}"
    }

    override fun getItemCount()=data.size

}