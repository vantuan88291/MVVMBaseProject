package com.tuan88291.mvvmpattern.data.remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import java.util.concurrent.TimeUnit

import com.tuan88291.mvvmpattern.utils.Common.DOMAIN


object ServiceGenerator {
    private val httpClient: OkHttpClient.Builder by lazy { OkHttpClient.Builder() }
    private val builder = Retrofit.Builder()
        .baseUrl(DOMAIN)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    // service have not token
    fun <S> createService(serviceClass: Class<S>): S {
        httpClient.apply {
            readTimeout(3, TimeUnit.MINUTES)
            connectTimeout(3, TimeUnit.MINUTES)
            addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("Accept", "application/json")
                    .method(chain.request().method(), chain.request().body())
                val request = requestBuilder.build()
                chain.proceed(request)
            }
        }

        val gson = GsonBuilder()
            .setLenient()
            .create()
        val client = httpClient.build()
        val retrofit = builder.client(client).addConverterFactory(GsonConverterFactory.create(gson)).build()
        return retrofit.create(serviceClass)
    }

    fun <S> createServiceToken(serviceClass: Class<S>): S {
        httpClient.apply {
            readTimeout(3, TimeUnit.MINUTES)
            connectTimeout(3, TimeUnit.MINUTES)
            addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .method(chain.request().method(), chain.request().body()).build()
                chain.proceed(requestBuilder)
            }
        }
        val client = httpClient.build()
        val retrofit = builder.client(client).build()
        return retrofit.create(serviceClass)
    }
}
