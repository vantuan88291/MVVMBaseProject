package com.tuan88291.mvvmpattern.view.fragment.homefragment

import androidx.lifecycle.MutableLiveData
import com.tuan88291.mvvmpattern.data.local.model.DataUser
import com.tuan88291.mvvmpattern.data.remote.ApiGenerator
import com.tuan88291.mvvmpattern.data.remote.BaseInteractor
import com.tuan88291.mvvmpattern.data.remote.CallApi
import com.tuan88291.mvvmpattern.view.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(api: ApiGenerator): BaseViewModel(), BaseInteractor {
    override val callAPi: CallApi = api.createApi()
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
        CoroutineScope(Main).launch {
            try {
                val data = withContext(IO) {callAPi.getList(1) }
                dataServer.postValue(data)
                isLoading.postValue(false)
            } catch (e: Exception) {
                isLoading.postValue(false)
                error.postValue(e.toString())
            }
        }
//        object : BaseRetrofit<DataUser>(callAPi.getList(1)) {
//            override fun onFail(err: String) {
//                error.postValue(err)
//            }
//
//            override fun onLoading() {
//                super.onLoading()
//                isLoading.postValue(true)
//            }
//
//            override fun onLoadComplete() {
//                super.onLoadComplete()
//                isLoading.postValue(false)
//            }
//
//            override fun getDispose(): AutoDisposable? {
//                return autodis
//            }
//
//            override fun onGetApiComplete(t: DataUser) {
//                dataServer.postValue(t)
//            }
//        }

    }
}