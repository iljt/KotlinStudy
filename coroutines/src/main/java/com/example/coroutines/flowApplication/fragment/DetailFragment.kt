package com.example.coroutines.flowApplication.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.databinding.FragmentDetailBinding
import com.example.coroutines.flowApplication.adapter.ArticleAdapter
import com.example.coroutines.flowApplication.viewmodel.ArticleViewModel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect

class DetailFragment : Fragment() {

    private val mBinding: FragmentDetailBinding by lazy {
        FragmentDetailBinding.inflate(layoutInflater)
    }

    private val articleViewModel by viewModels<ArticleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return mBinding.root
    }

    //获取输入框文字
    private fun TextView.textWatcherFlow(): Flow<String> = callbackFlow {
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                //offer(s.toString())
                trySend(s.toString()).isSuccess

            }

        }
        addTextChangedListener(textWatcher)
        //关闭的时候移除监听
        awaitClose { removeTextChangedListener(textWatcher) }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mBinding.apply {
            lifecycleScope.launchWhenCreated {
                searchEt.textWatcherFlow().collect {
                    Log.e("xxx", "search key word")
                    articleViewModel.searchArticle(it)
                }
            }

        }
        context?.let {
            val adapter = ArticleAdapter(it)
            mBinding.recycleView.layoutManager = LinearLayoutManager(it)
            mBinding.recycleView.adapter = adapter
            articleViewModel.articles.observe(viewLifecycleOwner) {list->
                adapter.setData(list)
            }
        }


    }

}