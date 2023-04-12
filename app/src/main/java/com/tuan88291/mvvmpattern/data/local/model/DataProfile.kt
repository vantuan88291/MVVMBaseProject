package com.tuan88291.mvvmpattern.data.local.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DataProfile {
    private val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    private val address: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun setData(name: String, address: String) {
        this.name.postValue(name)
        this.address.postValue(address)
    }

    fun getName(): LiveData<String> {
        return name
    }

    fun setName(name: String) {
        this.name.postValue(name)
    }

    fun getAddress(): LiveData<String> {
        return address
    }

    fun setAddress(address: String) {
        this.address.postValue(address)
    }
}