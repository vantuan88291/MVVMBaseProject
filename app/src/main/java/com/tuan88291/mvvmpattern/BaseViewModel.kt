package com.tuan88291.mvvmpattern

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel: ViewModel() {
    val error: MutableLiveData<Any> by lazy { MutableLiveData<Any>() }
    val isLoading: MutableLiveData<Boolean> by lazy { MutableLiveData<Boolean>() }
}