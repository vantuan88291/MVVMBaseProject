package com.tuan88291.mvvmpattern.data.remote


import com.tuan88291.mvvmpattern.data.local.model.DataUser
import retrofit2.http.GET
import retrofit2.http.Query

@JvmSuppressWildcards
interface CallApi {
    @GET("ssdusers11")
    suspend fun getList(@Query("sdsdpage") page: Int): DataUser
}
