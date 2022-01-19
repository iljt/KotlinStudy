package com.example.coroutines.flowApplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.databinding.FragmentNumberBinding
import com.example.coroutines.flowApplication.viewmodel.NumberViewModel
import kotlinx.coroutines.flow.collect

class NumberFragment : Fragment() {

    private val mBinding: FragmentNumberBinding by lazy {
        FragmentNumberBinding.inflate(layoutInflater)
    }

    private val numberViewmodel by viewModels<NumberViewModel>()

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
            plusBtn.setOnClickListener {
                numberViewmodel.increment( )

            }
            minusBtn.setOnClickListener {
                numberViewmodel.decrement()
            }
        }
        lifecycleScope.launchWhenCreated {
            numberViewmodel.number.collect {value->
                mBinding.apply {
                    numTv.text= "$value"
                }
            }
        }

    }

}