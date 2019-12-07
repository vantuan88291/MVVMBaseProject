package com.tuan88291.mvvmpattern.view.fragment.chat

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
        chatViewModel.getDataChat().observe(this, Observer<DataChat> { this.processData(it) })

//        binding?.btn?.setOnClickListener {
//            mSocket.emit("sendmsg", "i send new msg here")
//        }

    }

    private fun processData(item: DataChat) {

    }
    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(chatViewModel)
    }
}
