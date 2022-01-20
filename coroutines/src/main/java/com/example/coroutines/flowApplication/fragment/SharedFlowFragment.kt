package com.example.coroutines.flowApplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.databinding.FragmentNumberBinding
import com.example.coroutines.databinding.FragmentSharedFlowBinding
import com.example.coroutines.flowApplication.viewmodel.NumberViewModel
import com.example.coroutines.flowApplication.viewmodel.SharedFlowViewModel
import kotlinx.coroutines.flow.collect

class SharedFlowFragment : Fragment() {

    private val mBinding: FragmentSharedFlowBinding by lazy {
        FragmentSharedFlowBinding.inflate(layoutInflater)
    }

    private val sharedViewmodel by viewModels<SharedFlowViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return mBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mBinding.apply {
            startBtn.setOnClickListener {
                sharedViewmodel.startRefresh( )

            }
            stopBtn.setOnClickListener {
                sharedViewmodel.stoptRefresh()
            }
        }

    }

}