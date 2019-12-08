package com.tuan88291.mvvmpattern.view.fragment.chat

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Observer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.BaseFragment
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.data.local.model.DataChat
import com.tuan88291.mvvmpattern.databinding.AboutFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ChatFragment : BaseFragment() {
    private var binding: AboutFragmentBinding? = null
    private val chatViewModel: ChatViewModel by viewModel()
    override fun setView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.about_fragment, container, false)
        return binding!!.getRoot()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(chatViewModel)
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.list?.setmId(Build.MODEL)
        chatViewModel.getLoading().observe(this, Observer<Boolean> { this.loading(it) })
        chatViewModel.getDataChat().observe(this, Observer<DataChat> { this.processData(it) })
        chatViewModel.getAllDataChat().observe(this, Observer<MutableList<DataChat>> { this.processAllData(it) })
        binding?.send?.setOnClickListener {
            if (binding?.input?.text?.toString()!! == "") return@setOnClickListener
            chatViewModel.sendMsg(binding?.input?.text?.toString()!!)
            binding?.input?.setText("")
        }
    }

    private fun processData(item: DataChat) {
        binding?.list?.setData(item)
    }
    private fun processAllData(data: MutableList<DataChat>) {
        binding?.list?.addAllData(data)
    }
    private fun loading(load: Boolean) {
        binding?.loading?.visibility = if (load) View.VISIBLE else View.GONE
    }
    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(chatViewModel)
    }
}
