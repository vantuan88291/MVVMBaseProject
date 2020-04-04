package com.tuan88291.mvvmpattern.view.fragment.homefragment

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.tuan88291.mvvmpattern.data.local.model.DataUser
import com.tuan88291.mvvmpattern.data.remote.ApiGenerator
import com.tuan88291.mvvmpattern.data.remote.customcallback.BaseRetrofit
import com.tuan88291.mvvmpattern.utils.observe.AutoDisposable
import com.tuan88291.mvvmpattern.BaseViewModel
import com.tuan88291.mvvmpattern.view.adapter.AdapterStateLoad

class HomeViewModel(api: ApiGenerator): BaseViewModel(api) {
    private val state: MutableLiveData<State> by lazy { MutableLiveData<State>() }
    private val stateAdapter: MutableLiveData<AdapterStateLoad> by lazy { MutableLiveData<AdapterStateLoad>() }
    private val autodis = AutoDisposable(null)
    private var page: Int = 0
    fun getData(): MutableLiveData<State>{
        return this.state
    }
    fun getStateLoadAdapter(): MutableLiveData<AdapterStateLoad>{
        return this.stateAdapter
    }
    fun loadData(init: Boolean){
        if(init) {
            state.value = State.Loading(true)
            loadRepoData()
        } else {
            val state = stateAdapter.value
            if (state != null && state is AdapterStateLoad.LoadingSuccess && state !is AdapterStateLoad.FinishLoadMore) {
                loadRepoData()
            }
        }
    }
    private fun loadRepoData() {
        page += 1
        object : BaseRetrofit<DataUser>(callAPi.getList(page)) {
            override fun onFail(err: String) {
                state.value = State.Failure(err)
            }

            override fun onLoading() {
                super.onLoading()
                stateAdapter.value = AdapterStateLoad.Loading
            }

            override fun onLoadComplete() {
                super.onLoadComplete()
                state.value = State.Loading(false)
                stateAdapter.value = AdapterStateLoad.LoadingSuccess
            }

            override fun getDispose(): AutoDisposable? {
                return autodis
            }

            override fun onGetApiComplete(t: DataUser) {
                state.value = State.Success(t)
            }
        }
    }
    fun finishLoadMore() {
        stateAdapter.value = AdapterStateLoad.FinishLoadMore
    }
    fun dispose() {
        autodis.onDismiss()
    }
}