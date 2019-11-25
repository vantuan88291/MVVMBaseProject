package com.tuan88291.mvppatternkotlin.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tuan88291.mvppatternkotlin.data.local.entity.DataRoom

@Dao
interface QueriesDao {

    @Query("select * from user")
    fun getAll(): LiveData<List<DataRoom>>

    @Insert
    fun insertData(item: DataRoom)
}