package com.tuan88291.mvvmpattern.data.local.room.livedata

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tuan88291.mvvmpattern.data.local.entity.DataRoom
import com.tuan88291.mvvmpattern.data.local.room.QueriesDao

class DBmodel(val mRepository: DBRepository) : ViewModel(), QueriesDao {
    override fun getAll(): LiveData<List<DataRoom>> {
        return mRepository.getAll()
    }

    override fun insertData(item: DataRoom) {
            mRepository.insertData(item)
    }
}
