package com.tuan88291.mvvmpattern.view.fragment.homefragment

import androidx.lifecycle.*
import com.tuan88291.mvvmpattern.data.remote.ApiGenerator
import com.tuan88291.mvvmpattern.utils.Utils.getMessageExeption
import kotlinx.coroutines.Dispatchers.IO
import com.tuan88291.mvvmpattern.BaseViewModel
import kotlinx.coroutines.*
import com.tuan88291.mvvmpattern.view.adapter.AdapterStateLoad
import kotlinx.coroutines.Dispatchers.Main

class HomeViewModel(api: ApiGenerator): BaseViewModel(api), LifecycleObserver {
    private var job: Job? = null
    private val state: MutableLiveData<State> by lazy { MutableLiveData<State>() }
    private val stateAdapter: MutableLiveData<AdapterStateLoad> by lazy { MutableLiveData<AdapterStateLoad>() }
    private var page: Int = 0
    private var isLoadMore = true
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
            if (state != null && state is AdapterStateLoad.LoadingSuccess && isLoadMore) {
                loadRepoData()
            }
        }
    }
    private fun loadRepoData() {
        page += 1
        stateAdapter.value = AdapterStateLoad.Loading
        val err = CoroutineExceptionHandler { context, error ->
            state.value = State.Failure(getMessageExeption(error))
            state.value = State.Loading(false)
            stateAdapter.value = AdapterStateLoad.LoadingSuccess
        }
        job = CoroutineScope(err).launch {
            val data = withContext(IO) {callAPi.getList(page) }
            if (data.data?.size == 0) isLoadMore = false
            withContext(Main) {
                state.value = State.Success(data)
                state.value = State.Loading(false)
                stateAdapter.value = AdapterStateLoad.LoadingSuccess
            }
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        job?.cancel()
    }
}