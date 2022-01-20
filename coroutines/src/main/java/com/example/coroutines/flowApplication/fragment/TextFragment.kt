package com.example.coroutines.flowApplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.databinding.FragmentTextBinding
import com.example.coroutines.flowApplication.common.LocalEventBus
import kotlinx.coroutines.flow.collect

class TextFragment : Fragment() {

    private val mBinding: FragmentTextBinding by lazy {
        FragmentTextBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launchWhenCreated {
           LocalEventBus.events.collect {
               mBinding.apply {
                   tv.text=it.timestamp.toString()
               }
           }
        }

    }

}