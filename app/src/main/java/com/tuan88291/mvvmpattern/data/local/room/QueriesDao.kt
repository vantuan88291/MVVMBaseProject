package com.tuan88291.mvvmpattern.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tuan88291.mvvmpattern.data.local.entity.DataRoom
import io.reactivex.Completable

@Dao
interface QueriesDao {

    @Query("select * from user")
    fun getAll(): LiveData<List<DataRoom>>

    @Insert
    fun insertData(item: DataRoom) : Completable
}