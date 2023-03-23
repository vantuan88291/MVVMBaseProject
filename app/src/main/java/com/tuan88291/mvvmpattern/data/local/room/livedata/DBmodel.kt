package com.tuan88291.mvvmpattern.data.local.room.livedata

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tuan88291.mvvmpattern.data.local.entity.DataRoom
import com.tuan88291.mvvmpattern.data.local.room.QueriesDao
import com.tuan88291.mvvmpattern.utils.Utils

class DBmodel(val mRepository: iDBRepository) : ViewModel() {
    init {
        Utils.log("Init DBmodel", "init ok")
    }
    fun getAll(): LiveData<MutableList<DataRoom>> {
        return mRepository.getAll()
    }
    fun getLast(): LiveData<DataRoom> {
        return mRepository.getLast()
    }
    fun insertData(item: DataRoom) {
            mRepository.insertData(item)
    }
}
