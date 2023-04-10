package com.tuan88291.mvvmpattern.view.fragment.homefragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tuan88291.mvvmpattern.data.local.model.DataUser
import com.tuan88291.mvvmpattern.data.remote.ApiGenerator
import com.tuan88291.mvvmpattern.data.remote.customcallback.BaseRetrofit
import com.tuan88291.mvvmpattern.utils.observe.AutoDisposable
import com.tuan88291.mvvmpattern.BaseViewModel
import com.tuan88291.mvvmpattern.State
import com.tuan88291.mvvmpattern.data.local.entity.DataRoom
import com.tuan88291.mvvmpattern.data.local.room.livedata.DBmodel
import com.tuan88291.mvvmpattern.view.adapter.AdapterStateLoad
import kotlin.math.log

class HomeViewModel(api: ApiGenerator, val db: DBmodel): BaseViewModel(api) {
    private val stateAdapter: MutableLiveData<AdapterStateLoad> by lazy { MutableLiveData<AdapterStateLoad>() }
    private val autodis = AutoDisposable(null)
    private var page: Int = 0
    private var isLoadMore = true

    fun getAllDatabase(): LiveData<MutableList<DataRoom>> {
        return db.getAll()
    }
    fun insertDatabase(item: DataRoom) {
        db.insertData(item)
    }
    fun getData(): MutableLiveData<State>{
        Log.d("TAG", "getData: ")
        return this.state
    }
    fun getStateLoadAdapter(): MutableLiveData<AdapterStateLoad>{
        return this.stateAdapter
    }
    fun loadData(init: Boolean): HomeViewModel{
        Log.d("TAG", "loadData: ")
        if(init) {
            state.value = State.Loading(true)
            loadRepoData()
        } else {
            val state = stateAdapter.value
            if (state != null && state is AdapterStateLoad.LoadingSuccess && isLoadMore) {
                loadRepoData()
            }
        }
        return this
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
                if (t.data?.size == 0) isLoadMore = false
            }
        }
    }
    fun dispose() {
        autodis.onDismiss()
    }
}