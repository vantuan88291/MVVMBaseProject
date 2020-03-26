package com.tuan88291.mvvmpattern.view.fragment.homefragment

import androidx.lifecycle.*
import com.tuan88291.mvvmpattern.data.remote.ApiGenerator
import com.tuan88291.mvvmpattern.utils.Utils.getMessageExeption
import kotlinx.coroutines.Dispatchers.IO
import com.tuan88291.mvvmpattern.BaseViewModel
import kotlinx.coroutines.*

class HomeViewModel(api: ApiGenerator): BaseViewModel(api), LifecycleObserver {
    private var job: Job? = null
    private val state: MutableLiveData<State> by lazy { MutableLiveData<State>() }
    fun getData(): MutableLiveData<State>{
        return this.state
    }
    fun loadData(){
        state.value = State.Loading(true)
        val err = CoroutineExceptionHandler { context, error ->
            state.value = State.Failure(getMessageExeption(error))
            state.value = State.Loading(false)
        }
        job = CoroutineScope(err).launch {
            val data = withContext(IO) {callAPi.getList(1) }
            state.value = State.Success(data)
            state.value = State.Loading(false)
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        job?.cancel()
    }
}