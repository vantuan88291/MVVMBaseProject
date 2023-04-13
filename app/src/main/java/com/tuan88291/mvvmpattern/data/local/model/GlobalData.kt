package com.tuan88291.mvvmpattern.data.local.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tuan88291.mvvmpattern.data.local.model.user.DataProfile

class GlobalData {
    private val user: MutableLiveData<DataProfile> by lazy { MutableLiveData<DataProfile>() }
    fun getUser(): LiveData<DataProfile> {
        return user
    }

    fun setUser(user: DataProfile) {
        this.user.postValue(user)
    }
}