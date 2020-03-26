package com.tuan88291.mvvmpattern.view.fragment.homefragment

import androidx.lifecycle.MutableLiveData
import com.tuan88291.mvvmpattern.data.local.model.DataUser
import com.tuan88291.mvvmpattern.data.remote.ApiGenerator
import com.tuan88291.mvvmpattern.data.remote.BaseInteractor
import com.tuan88291.mvvmpattern.data.remote.CallApi
import com.tuan88291.mvvmpattern.data.remote.customcallback.BaseRetrofit
import com.tuan88291.mvvmpattern.utils.observe.AutoDisposable
import com.tuan88291.mvvmpattern.BaseViewModel

class HomeViewModel(api: ApiGenerator): BaseViewModel(api) {
    private val state: MutableLiveData<State> by lazy { MutableLiveData<State>() }
    private val autodis = AutoDisposable(null)
    fun getData(): MutableLiveData<State>{
        return this.state
    }
    fun loadData(){
        object : BaseRetrofit<DataUser>(callAPi.getList(1)) {
            override fun onFail(err: String) {
                state.value = State.Failure(err)
            }

            override fun onLoading() {
                super.onLoading()
                state.value = State.Loading(true)
            }

            override fun onLoadComplete() {
                super.onLoadComplete()
                state.value = State.Loading(false)
            }

            override fun getDispose(): AutoDisposable? {
                return autodis
            }

            override fun onGetApiComplete(t: DataUser) {
                state.value = State.Success(t)
            }
        }
    }
    fun dispose() {
        autodis.onDismiss()
    }
}