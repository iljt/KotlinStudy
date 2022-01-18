package com.example.coroutines.flowApplication.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coroutines.R
import com.example.coroutines.databinding.FragmentHomeBinding
import com.example.coroutines.databinding.FragmentUserBinding
import com.example.coroutines.flowApplication.adapter.UserAdapter
import com.example.coroutines.flowApplication.viewmodel.UserViewModel
import kotlinx.coroutines.flow.collect

class UserFragment : Fragment() {

    private val mBinding: FragmentUserBinding by lazy {
        FragmentUserBinding.inflate(layoutInflater)
    }

    private val userViewmodel by viewModels<UserViewModel>()

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
            addUserBtn.setOnClickListener {
                userViewmodel.insertUser(
                    userIdEt.text.toString(),
                    firstNameEt.text.toString(),
                    lastNameEt.text.toString()
                )
            }
            deleteUserBtn.setOnClickListener {
                userViewmodel.deleteUser(userIdEt.text.toString())
            }
        }
        context?.let {
            val userAdapter = UserAdapter(it)
            mBinding.recycleView.layoutManager = LinearLayoutManager(requireContext())
            mBinding.recycleView.adapter = userAdapter
            lifecycleScope.launchWhenCreated {
                userViewmodel.getAllUser().collect { userList ->
                    userAdapter.setData(userList)
                }
            }
        }


    }

}