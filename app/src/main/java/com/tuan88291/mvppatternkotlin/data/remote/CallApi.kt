package com.tuan88291.mvppatternkotlin.data.remote


import com.tuan88291.mvppatternkotlin.data.local.model.DataUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

@JvmSuppressWildcards
interface CallApi {
    @GET("users")
    fun getList(@Query("page") page: String): Call<DataUser>
}
