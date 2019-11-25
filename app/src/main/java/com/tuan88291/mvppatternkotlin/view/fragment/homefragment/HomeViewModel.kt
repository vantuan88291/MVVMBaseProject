package com.tuan88291.mvppatternkotlin.view.fragment.homefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan88291.mvppatternkotlin.data.local.model.DataUser
import com.tuan88291.mvppatternkotlin.data.remote.ApiUtil
import com.tuan88291.mvppatternkotlin.data.remote.BaseInteractor
import com.tuan88291.mvppatternkotlin.data.remote.CallApi
import com.tuan88291.mvppatternkotlin.data.remote.customcallback.BaseRetrofit
import retrofit2.Response

class HomeViewModel(): ViewModel(), BaseInteractor {
    override val callAPi: CallApi = ApiUtil.createApi()

    private val dataServer: MutableLiveData<DataUser> by lazy { MutableLiveData<DataUser>() }
    private val isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    fun getData(): MutableLiveData<DataUser>{
        return this.dataServer
    }
    fun loading(): MutableLiveData<Boolean>{
        return this.isLoading
    }
    fun loadData(){
        object : BaseRetrofit<DataUser>(callAPi.getList("1")) {
            override fun onGetApiComplete(response: Response<DataUser>) {
                dataServer.postValue(response.body())
            }

            override fun onFail(err: String) {

            }

            override fun onLoading() {
                super.onLoading()
                isLoading.postValue(true)
            }

            override fun onLoadComplete() {
                super.onLoadComplete()
                isLoading.postValue(false)
            }
        }
    }
}