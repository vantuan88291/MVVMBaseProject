package com.tuan88291.mvvmpattern.view.fragment.homefragment

import androidx.lifecycle.*
import com.tuan88291.mvvmpattern.data.local.model.DataUser
import com.tuan88291.mvvmpattern.data.remote.ApiGenerator
import com.tuan88291.mvvmpattern.data.remote.BaseInteractor
import com.tuan88291.mvvmpattern.data.remote.CallApi
import com.tuan88291.mvvmpattern.utils.Utils.getMessageExeption
import com.tuan88291.mvvmpattern.utils.observe.AutoDisposable
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import com.tuan88291.mvvmpattern.view.BaseViewModel
import kotlinx.coroutines.*

class HomeViewModel(api: ApiGenerator): BaseViewModel(), BaseInteractor, LifecycleObserver {
    override val callAPi: CallApi = api.createApi()
    private var job: Job? = null
    private val dataServer: MutableLiveData<DataUser> by lazy { MutableLiveData<DataUser>() }
    fun getData(): MutableLiveData<DataUser>{
        return this.dataServer
    }
    fun loading(): MutableLiveData<Boolean>{
        return this.isLoading
    }
    fun error(): MutableLiveData<Any>{
        return this.error
    }
    fun loadData(){
        isLoading.postValue(true)
        val err = CoroutineExceptionHandler { context, error ->
            this.error.postValue(getMessageExeption(error))
            isLoading.postValue(false)
        }
        job = CoroutineScope(err).launch {
            val data = withContext(IO) {callAPi.getList(1) }
            dataServer.postValue(data)
            isLoading.postValue(false)
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy() {
        job?.cancel()
    }
}