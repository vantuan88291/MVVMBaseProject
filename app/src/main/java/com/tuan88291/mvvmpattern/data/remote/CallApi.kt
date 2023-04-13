package com.tuan88291.mvvmpattern.data.remote


import com.tuan88291.mvvmpattern.data.local.model.CommonData
import com.tuan88291.mvvmpattern.data.local.model.DataUser
import com.tuan88291.mvvmpattern.data.local.model.login.DataLogin
import com.tuan88291.mvvmpattern.data.local.model.login.ParamLogin
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

@JvmSuppressWildcards
interface CallApi {
    @GET("users")
    fun getList(@Query("page") page: Int): Observable<DataUser>

    @POST("auth/login")
    fun login(@Body params: ParamLogin): Observable<CommonData<DataLogin>>
}
