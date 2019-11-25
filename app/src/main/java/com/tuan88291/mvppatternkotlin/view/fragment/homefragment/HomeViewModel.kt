package com.tuan88291.mvppatternkotlin.view.fragment.homefragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan88291.mvppatternkotlin.data.local.model.DataUser
import com.tuan88291.mvppatternkotlin.data.remote.ApiUtil
import com.tuan88291.mvppatternkotlin.data.remote.BaseInteractor
import com.tuan88291.mvppatternkotlin.data.remote.CallApi
import com.tuan88291.mvppatternkotlin.data.remote.customcallback.BaseRetrofit
import retrofit2.Response

class HomeViewModel(val v: HomeContract): ViewModel(), BaseInteractor {
    override val callAPi: CallApi = ApiUtil.createApi()

    private val dataServer: MutableLiveData<DataUser> by lazy { MutableLiveData<DataUser>() }
    fun getData(): MutableLiveData<DataUser>{
        return this.dataServer
    }
    fun loadData(){
        object : BaseRetrofit<DataUser>(callAPi.getList("1"), v) {
            override fun onGetApiComplete(response: Response<DataUser>) {
                dataServer.postValue(response.body())
            }

            override fun onFail(err: String) {
                v.onError(err)
            }

            override fun onLoading() {
                super.onLoading()
                v.onLoading()
            }

            override fun onLoadComplete() {
                super.onLoadComplete()
                v.onLoadComplete()
            }
        }
    }
}