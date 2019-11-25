package com.tuan88291.mvvmpattern.data.remote.customcallback

import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class CallBackBase<T> : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        LogUtils.a(call.request().toString() + "\n" + Gson().toJson(response.body()))
        onSuccess(call, response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        LogUtils.a(call.request().toString() + "\n" + t.message)
        onError(call, t)
    }

    protected abstract fun onSuccess(call: Call<T>, response: Response<T>)
    protected abstract fun onError(call: Call<T>, t: Throwable)
}
