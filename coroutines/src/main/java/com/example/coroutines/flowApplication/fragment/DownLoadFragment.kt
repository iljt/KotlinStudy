package com.example.coroutines.flowApplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.coroutines.databinding.FragmentDownloadBinding
import com.example.coroutines.flowApplication.download.DownLoadManager
import com.example.coroutines.flowApplication.download.DownLoadState
import kotlinx.coroutines.flow.collect
import java.io.File

class DownLoadFragment : Fragment() {
    var url="https://p0.ssl.qhimgs1.com/sdr/400__/t01dcc835ad5f43ecd0.jpg"
    var url2="https://desk-fd.zol-img.com.cn/g5/M00/02/00/ChMkJlbKw5aIY6PYAA0XnsktgwEAALG5QPtg4wADRe2915.jpg"
    private val mBinding:FragmentDownloadBinding by lazy{
        FragmentDownloadBinding.inflate(layoutInflater)
    }
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?,   savedInstanceState: Bundle? ): View? {

        return mBinding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            context?.apply {
                val file= File(getExternalFilesDir(null)?.path,"pic.jpg")
                DownLoadManager.downLoad(url,file).collect {  state->
                    when(state){
                        is DownLoadState.Progress->{
                            mBinding.apply {
                                progressBar.progress=state.progress
                                progressTv.text="${state.progress}"
                            }
                        }
                        is DownLoadState.Error->{
                          Toast.makeText(context,"下载错误",Toast.LENGTH_SHORT).show()
                        }
                        is DownLoadState.DownLoadSuccess->{
                            mBinding.apply {
                                progressBar.progress=100
                                progressTv.text="100%"
                            }
                            Toast.makeText(context,"下载完成",Toast.LENGTH_SHORT).show()
                        }
                        else->{
                            Toast.makeText(context,"下载失败",Toast.LENGTH_SHORT).show()
                        }
                    }

                }

            }

        }

    }
}