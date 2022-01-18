package com.example.coroutines.flowApplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.coroutines.databinding.ActivityFlowApplicationBinding
/**

 * Created  by Administrator on 2022/1/17 23:30

 */
class FlowDownLoadFileActivity : AppCompatActivity() {

    private val mBinding:ActivityFlowApplicationBinding by lazy{
        ActivityFlowApplicationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }
}