package com.tuan88291.mvvmpattern.data.remote


import com.tuan88291.mvvmpattern.data.local.model.DataUser
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

@JvmSuppressWildcards
interface CallApi {
    @GET("users")
    fun getList(@Query("page") page: Int): Observable<DataUser>
}
