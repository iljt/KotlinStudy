package com.example.kotlinstudy.kotlinAdvanced.coroutines.use2.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinstudy.R
import com.example.kotlinstudy.databinding.ActivityMain5Binding
import com.example.kotlinstudy.kotlinAdvanced.coroutines.use2.viewmodel.APIViewModel
import kotlinx.android.synthetic.main.activity_main5.*

class MainActivity5 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // DataBinding必须是AndroidX的环境  ComponentActivity LifecycleOwner

        // 绑定DataBinding
        val binding = DataBindingUtil.setContentView<ActivityMain5Binding>(this, R.layout.activity_main5)
        binding.lifecycleOwner = this

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            APIViewModel::class.java)

        binding.vm = viewModel

        bt.setOnClickListener { viewModel.requestLogin("vip", "123456") }
    }

}