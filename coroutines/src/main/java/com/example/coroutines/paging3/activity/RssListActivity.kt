package com.example.coroutines.paging3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.databinding.ActivityMoveListBinding
import com.example.coroutines.paging3.adapter.RssAdapter
import com.example.coroutines.paging3.adapter.RssLoadMoreAdapter
import com.example.coroutines.paging3.viewmodel.MainViewModel
import com.example.coroutines.paging3.viewmodel.RssViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RssListActivity : AppCompatActivity() {

   private val mBinding : ActivityMoveListBinding  by lazy{
        ActivityMoveListBinding.inflate(layoutInflater)
   }
    private val  rssViewModel by viewModels<RssViewModel>()
    private  val  mainViewModel :MainViewModel by viewModels()
    private  val  rssAdapter by lazy{
        RssAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        mBinding.recycleView.adapter =rssAdapter
        mBinding.recycleView.layoutManager = LinearLayoutManager(this)
        //使用paging3
        /*   mBinding.apply {
             recycleView.adapter=rssAdapter.withLoadStateFooter(RssLoadMoreAdapter(this@RssListActivity))
             swipeRefreshLayout.setOnRefreshListener {
                 rssAdapter.refresh()
             }
         }
       lifecycleScope.launchWhenCreated {
             rssViewModel.queryRssList().collectLatest {
                 rssAdapter.submitData(it)
             }
         }
         lifecycleScope.launchWhenCreated {
             //监听刷新的状态
             rssAdapter.loadStateFlow.collectLatest { state->
                 mBinding.swipeRefreshLayout.isRefreshing = state.refresh is LoadState.Loading

             }
         }*/
       //使用Mediator
        mBinding.apply {
            recycleView.adapter=rssAdapter.withLoadStateFooter(RssLoadMoreAdapter(this@RssListActivity))
            swipeRefreshLayout.setOnRefreshListener {
                rssAdapter.refresh()
            }
        }
        mainViewModel.data.observe(this){
            rssAdapter.submitData(lifecycle,it)
        }
        lifecycleScope.launchWhenCreated {
            //监听刷新的状态
            rssAdapter.loadStateFlow.collectLatest { state->
                mBinding.swipeRefreshLayout.isRefreshing = state.refresh is LoadState.Loading

            }
        }
    }
}