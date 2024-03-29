package com.tuan88291.mvvmpattern.view.fragment.chat

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.tuan88291.mvvmpattern.BaseFragment
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.data.local.model.DataChat
import com.tuan88291.mvvmpattern.databinding.AboutFragmentBinding
import com.tuan88291.mvvmpattern.utils.TextWatcherWrap
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
        chatViewModel.let {
            it.getTyping().observe(this, Observer { this.onTyping(it) })
            it.getLoading().observe(this, Observer { this.setLoading(it) })
            it.getDataChat().observe(this, Observer { this.processData(it) })
            it.getAllDataChat().observe(this, Observer { this.processAllData(it) })
        }

        binding?.send?.setOnClickListener {
            if (binding?.input?.text?.toString()!! == "") return@setOnClickListener
            chatViewModel.sendMsg(binding?.input?.text?.toString()!!)
            binding?.input?.setText("")
        }
        binding?.input?.addTextChangedListener(object : TextWatcherWrap() {
            override fun onTextChangedWrap(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                chatViewModel.emitTyping()
            }
        })
        mContext()?.setUpTyping()
    }

    private fun processData(item: DataChat) {
        binding?.list?.setData(item)
    }
    private fun processAllData(data: MutableList<DataChat>) {
        binding?.list?.addAllData(data)
    }
    private fun onTyping(typings: String) {
        if (typings !== "") {
            mContext()?.setTyping(typings)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(chatViewModel)
    }
}
