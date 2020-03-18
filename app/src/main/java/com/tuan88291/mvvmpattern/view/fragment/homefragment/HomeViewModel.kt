package com.tuan88291.mvvmpattern.view.fragment.homefragment

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan88291.mvvmpattern.data.local.model.DataUser
import com.tuan88291.mvvmpattern.data.local.model.DetailUser
import com.tuan88291.mvvmpattern.data.remote.ApiGenerator
import com.tuan88291.mvvmpattern.data.remote.BaseInteractor
import com.tuan88291.mvvmpattern.data.remote.CallApi
import com.tuan88291.mvvmpattern.data.remote.customcallback.BaseRetrofit
import com.tuan88291.mvvmpattern.utils.observe.AutoDisposable
import com.tuan88291.mvvmpattern.utils.observe.addTo
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function3
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscriber

class HomeViewModel(api: ApiGenerator): ViewModel(), BaseInteractor {
    override val callAPi: CallApi = api.createApi()

    private val dataServer: MutableLiveData<MutableList<DetailUser>> by lazy { MutableLiveData<MutableList<DetailUser>>() }
    private val isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
    private val error: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val autodis = AutoDisposable(null)
    fun getData(): MutableLiveData<MutableList<DetailUser>>{
        return this.dataServer
    }
    fun loading(): MutableLiveData<Boolean>{
        return this.isLoading
    }
    fun error(): MutableLiveData<String>{
        return this.error
    }
    @SuppressLint("CheckResult")
    fun loadData(){
        isLoading.postValue(true)
        Observable.merge(callAPi.getList(1), callAPi.getList2(2))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(subrice())
            .addTo(autodis)

    }
    fun subrice(): DisposableObserver<DataUser> {
        return object : DisposableObserver<DataUser>() {
            override fun onComplete() {
                Log.d("ok", "ok")
                isLoading.postValue(false)
            }
            override fun onNext(t: DataUser) {
                Log.d("ok", "ok")
                dataServer.postValue(t.data)
            }

            override fun onError(e: Throwable) {
                Log.d("ok", "ok")
            }

        }
    }
    fun convertData(t1: DataUser, t2: DataUser): MutableList<DetailUser> {
        val data = mutableListOf<DetailUser>()
        data.addAll(t1.data!!)
        data.addAll(t2.data!!)
        return data
    }
    fun loadDataZip() {
        isLoading.postValue(true)
        val ob1 = callAPi.getList(1)
        val ob2 = callAPi.getList2(2)
        val result: Observable<MutableList<DetailUser>> = Observable.zip(ob1, ob2,
            BiFunction<DataUser, DataUser, MutableList<DetailUser>>{t1, t2 -> convertData(t1, t2)}
        ).subscribeOn(Schedulers.io())
        result.subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<MutableList<DetailUser>>() {
                override fun onComplete() {
                    isLoading.postValue(false)
                }

                override fun onNext(t: MutableList<DetailUser>) {
                    dataServer.postValue(t)
                }

                override fun onError(e: Throwable) {
                }

            })
            .addTo(autodis)
    }
    fun dispose() {
        autodis.onDismiss()
    }
}