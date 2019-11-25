package com.tuan88291.mvvmpattern.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtil {
    fun createApi(): CallApi {
        return ServiceGenerator.createService(CallApi::class.java)
    }

    fun createApiOtherDomain(): CallApi {
        val builder = Retrofit.Builder()
            .baseUrl("https://googleapis.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
        return builder.build().create(CallApi::class.java)
    }

    fun createTokenApi(): CallApi {
        return ServiceGenerator.createServiceToken(CallApi::class.java)
    }
}
