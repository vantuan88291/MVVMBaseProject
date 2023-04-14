package com.tuan88291.mvvmpattern.view.fragment.login

import androidx.lifecycle.LiveData
import com.tuan88291.mvvmpattern.data.local.model.CommonData
import com.tuan88291.mvvmpattern.data.local.model.GlobalData
import com.tuan88291.mvvmpattern.data.local.model.login.DataLogin
import com.tuan88291.mvvmpattern.data.local.model.login.ParamLogin
import com.tuan88291.mvvmpattern.data.local.model.user.DataProfile
import com.tuan88291.mvvmpattern.data.remote.ApiGenerator
import com.tuan88291.mvvmpattern.data.remote.customcallback.BaseRetrofit
import com.tuan88291.mvvmpattern.utils.Common
import com.tuan88291.mvvmpattern.utils.SharedPrefs
import com.tuan88291.mvvmpattern.utils.observe.AutoDisposable
import com.tuan88291.mvvmpattern.view.fragment.BaseViewModel
import com.tuan88291.mvvmpattern.view.fragment.State
import org.koin.core.KoinComponent
import org.koin.core.inject

class LoginViewModel(val api: ApiGenerator): BaseViewModel(api, true), KoinComponent {
    private val profile: GlobalData by inject()

    val params: ParamLogin by lazy { ParamLogin() }

    fun getParam(): ParamLogin {
        return this.params
    }

    fun getData(): LiveData<State> {
        return this.state
    }

    fun onLogin() {
        object : BaseRetrofit<CommonData<DataLogin>>(callAPi.login(params)) {
            override fun onFail(err: String) {
                state.value = State.Failure(err)
            }

            override fun onGetApiComplete(t: CommonData<DataLogin>) {
                SharedPrefs.instance?.put(Common.TOKEN, t.data?.accessToken)
                state.value = State.Success(t.data)
                //update token
                callAPi = api.createTokenApi()
                getProfile()
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
    fun getProfile(): LoginViewModel {
        object : BaseRetrofit<CommonData<DataProfile>>(callAPi.getProfile()) {
            override fun onFail(err: String) {
                state.value = State.Failure(err)
            }

            override fun onGetApiComplete(t: CommonData<DataProfile>) {
                if (t.data != null) {
                    profile.setUser(t.data!!)
                }
                state.value = State.Success(true)
            }

            override fun getDispose(): AutoDisposable? {
                return null
            }
        }
        return this
    }
}