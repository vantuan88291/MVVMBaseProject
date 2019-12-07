package com.tuan88291.mvvmpattern.view.fragment.about

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.tuan88291.mvvmpattern.BaseFragment
import com.tuan88291.mvvmpattern.R
import com.tuan88291.mvvmpattern.databinding.AboutFragmentBinding
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter


class About : BaseFragment() {
    private var binding: AboutFragmentBinding? = null
    private val mSocket: Socket by lazy { IO.socket("http://192.168.31.196:3000") }
    private var mHandler: Handler? = null
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
        mHandler = Handler()
        mSocket.connect()
        mSocket.on("newmsg", onNewMsg)
    }

    override fun viewCreated(view: View, savedInstanceState: Bundle?) {

        binding?.btn?.setOnClickListener {
            mSocket.emit("sendmsg", "i send new msg here")
        }

    }
    private val onNewMsg = object : Emitter.Listener {

        override fun call(vararg args: Any?) {
            mHandler?.post(Runnable {
                binding?.title?.text = args[0].toString()
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mSocket.disconnect()
    }
}
