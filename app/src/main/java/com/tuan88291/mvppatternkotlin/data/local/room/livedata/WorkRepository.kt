package com.tuan88291.mvppatternkotlin.data.local.room.livedata

import android.app.Application
import androidx.lifecycle.LiveData
import com.tuan88291.mvppatternkotlin.data.local.entity.DataRoom
import com.tuan88291.mvppatternkotlin.data.local.room.AppDatabase
import com.tuan88291.mvppatternkotlin.data.local.room.QueriesDao
import com.tuan88291.mvppatternkotlin.utils.observe.AutoDisposable
import com.tuan88291.mvppatternkotlin.utils.observe.ObserveEasy

class WorkRepository internal constructor(application: Application) {
    private val mQueries: QueriesDao
    internal val allData: LiveData<List<DataRoom>>

    init {
        val db = AppDatabase.getAppDatabase(application)
        mQueries = db.queries()
        allData = mQueries.getAll()
    }

    fun insert(data: DataRoom) {
        object : ObserveEasy(){
            override fun doBackground(): Any? {
                mQueries.insertData(data)
                return false
            }
            override fun getDispose(): AutoDisposable? {
                return null
            }
        }
    }
}
