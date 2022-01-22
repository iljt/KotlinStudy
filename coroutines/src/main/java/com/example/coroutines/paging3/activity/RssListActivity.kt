package com.example.coroutines.paging3.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.databinding.ActivityMoveListBinding
import com.example.coroutines.paging3.adapter.RssAdapter
import com.example.coroutines.paging3.viewmodel.RssViewModel
import kotlinx.coroutines.flow.collectLatest

class RssListActivity : AppCompatActivity() {

   private val mBinding : ActivityMoveListBinding  by lazy{
        ActivityMoveListBinding.inflate(layoutInflater)
   }
    private val  rssViewModel by viewModels<RssViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val rssAdapter=RssAdapter(this)
        mBinding.recycleView.layoutManager = LinearLayoutManager(this)
        mBinding.apply {
            recycleView.adapter=rssAdapter
        }
        lifecycleScope.launchWhenCreated {
            rssViewModel.queryRssList().collectLatest {
                rssAdapter.submitData(it)
            }
        }
    }
}