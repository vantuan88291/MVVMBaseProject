package com.tuan88291.mvvmpattern.view.fragment.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tuan88291.mvvmpattern.data.local.model.CommonData
import com.tuan88291.mvvmpattern.data.local.model.login.DataLogin
import com.tuan88291.mvvmpattern.data.local.model.login.ParamLogin
import com.tuan88291.mvvmpattern.data.remote.ApiGenerator
import com.tuan88291.mvvmpattern.data.remote.customcallback.BaseRetrofit
import com.tuan88291.mvvmpattern.utils.Common
import com.tuan88291.mvvmpattern.utils.SharedPrefs
import com.tuan88291.mvvmpattern.utils.observe.AutoDisposable
import com.tuan88291.mvvmpattern.view.fragment.BaseViewModel
import com.tuan88291.mvvmpattern.view.fragment.State

class LoginViewModel(api: ApiGenerator): BaseViewModel(api, true) {
    val params: MutableLiveData<ParamLogin> by lazy { MutableLiveData<ParamLogin>() }

    fun onSetValue(name: String, pass: String) {
        params.value = ParamLogin(email = name, password = pass)
    }

    fun getParam(): LiveData<ParamLogin> {
        return this.params
    }

    fun getData(): LiveData<State> {
        return this.state
    }

    fun onLogin() {
        object : BaseRetrofit<CommonData<DataLogin>>(callAPi.login(params.value!!)) {
            override fun onFail(err: String) {
                state.value = State.Failure(err)
            }

            override fun onGetApiComplete(t: CommonData<DataLogin>) {
                SharedPrefs.instance?.put(Common.TOKEN, t.data?.accessToken)
                state.value = State.Success(t)
            }

            override fun getDispose(): AutoDisposable? {
                return null
            }

            override fun onLoading() {
                super.onLoading()
                state.value = State.Loading(true)
            }

            override fun onLoadComplete() {
                super.onLoadComplete()
                state.value = State.Loading(false)
            }
        }
    }
}