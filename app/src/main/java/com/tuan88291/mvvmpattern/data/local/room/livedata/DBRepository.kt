package com.tuan88291.mvvmpattern.data.local.room.livedata

import androidx.lifecycle.LiveData
import com.tuan88291.mvvmpattern.data.local.entity.DataRoom
import com.tuan88291.mvvmpattern.data.local.room.QueriesDao
import com.tuan88291.mvvmpattern.utils.observe.AutoDisposable
import com.tuan88291.mvvmpattern.utils.observe.ObserveEasy

class DBRepository internal constructor(val mQueries: QueriesDao): QueriesDao {
    override fun getAll(): LiveData<List<DataRoom>> {
        return mQueries.getAll()
    }

    override fun insertData(item: DataRoom) {
        object : ObserveEasy(){
            override fun doBackground(): Any? {
                mQueries.insertData(item)
                return false
            }
            override fun getDispose(): AutoDisposable? {
                return null
            }
        }
    }
}
