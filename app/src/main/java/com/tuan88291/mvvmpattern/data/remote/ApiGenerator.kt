package com.tuan88291.mvvmpattern.data.remote

import com.tuan88291.mvvmpattern.data.remote.service.iServiceApi

class ApiGenerator(val service: iServiceApi) {

    fun processCreateApi(isToken: Boolean): CallApi {
        if (isToken) {
            return createTokenApi()
        }
        return createApi()
    }
    fun createApi(): CallApi {
        return service.createService(CallApi::class.java)
    }

    fun createTokenApi(): CallApi {
        return service.createServiceToken(CallApi::class.java)
    }
}