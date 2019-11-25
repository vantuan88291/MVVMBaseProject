package com.tuan88291.mvppatternkotlin.data.remote.customcallback

import com.blankj.utilcode.util.LogUtils
import com.tuan88291.mvppatternkotlin.BaseView
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

import java.io.IOException

abstract class BaseRetrofit<T>(callback: Call<T>, internal var view: BaseView) {

    init {
        getRetrofit(callback)
    }

    private fun getRetrofit(callback: Call<T>?) {
        onLoading()
        if (callback != null) {
            callback.enqueue(object : CallBackBase<T>() {
                public override fun onSuccess(call: Call<T>, response: Response<T>) {
                    if (response.isSuccessful) {
                        try {
                            onGetApiComplete(response)
                        } catch (e: Exception) {
                            onFail(e.localizedMessage)
                            LogUtils.a("Exeption fire: $e")
                        }

                    } else {
                        onFail(getMessage(response))
                        view.setErrorParent(getMessage(response))
                        LogUtils.a("response not successful: $response")

                    }
                    onLoadComplete()
                }

                public override fun onError(call: Call<T>, t: Throwable) {
                    if (t is IOException) {
                        onFail("Check your network")
                    } else {
                        onFail(t.localizedMessage)
                    }
                    LogUtils.a("onError: $t")
                    onLoadComplete()
                }
            })

        } else {
            onFail("callback can not be null")
            LogUtils.a("callback null")
            onLoadComplete()
        }

    }

    private fun getMessage(response: Response<T>): String {
        var mess = ""
        try {
            val errorBody = response.errorBody()!!.string()
            val jObjError = JSONObject(errorBody)
            mess = jObjError.getString("message")
        } catch (e: Exception) {
            mess = "Can not get message!"
            LogUtils.a("Can not get message!", response.toString())

        }

        return mess
    }

    protected abstract fun onGetApiComplete(response: Response<T>)

    protected open fun onLoading() {

    }

    protected open fun onLoadComplete() {

    }

    protected abstract fun onFail(err: String)

}
