package com.tuan88291.mvvmpattern.view.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuan88291.mvvmpattern.data.remote.ApiGenerator
import com.tuan88291.mvvmpattern.data.remote.BaseInteractor
import com.tuan88291.mvvmpattern.data.remote.CallApi

open class BaseViewModel(api: ApiGenerator, token: Boolean): ViewModel(), BaseInteractor {
    override var callAPi: CallApi = api.processCreateApi(token)
    val state: MutableLiveData<State> by lazy { MutableLiveData<State>() }
}