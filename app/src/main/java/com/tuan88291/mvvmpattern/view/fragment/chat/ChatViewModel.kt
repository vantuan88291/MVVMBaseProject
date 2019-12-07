package com.tuan88291.mvvmpattern.view.fragment.chat

import android.R.id.message
import androidx.lifecycle.*
import com.tuan88291.mvvmpattern.data.local.model.DataChat
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import org.json.JSONObject


class ChatViewModel: ViewModel(), LifecycleObserver {
    private val data: MutableLiveData<DataChat> by lazy { MutableLiveData<DataChat>() }
    private val mSocket: Socket by lazy { IO.socket("http://192.168.31.196:3000") }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreateSocket() {
        mSocket.connect()
        mSocket.on("newmsg", onNewMsg)
        initUser()
    }
    private fun initUser() {
        val jsonObject = JSONObject()
        jsonObject.put("id", 12)
        jsonObject.put("name", "tuan")
        mSocket.emit("initUser", jsonObject)
    }
    private val onNewMsg = object : Emitter.Listener {

        override fun call(vararg args: Any?) {

        }
    }
    fun getDataChat(): MutableLiveData<DataChat>{
        return this.data
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onDestroySocket() {
        mSocket.disconnect()
    }
}