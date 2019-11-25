package com.tuan88291.mvvmpattern.data.local.room.livedata

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tuan88291.mvvmpattern.data.local.entity.DataRoom

class MyViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: WorkRepository

    val allData: LiveData<List<DataRoom>>

    init {
        mRepository = WorkRepository(application)
        allData = mRepository.allData
    }

    fun insert(word: DataRoom) {
        mRepository.insert(word)
    }
}
