package com.tuan88291.mvvmpattern.data.remote.service

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.tuan88291.mvvmpattern.BuildConfig
import com.tuan88291.mvvmpattern.utils.Common
import com.tuan88291.mvvmpattern.utils.SharedPrefs
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ServiceApi: iServiceApi {
    private val logging = HttpLoggingInterceptor()
    private val httpClient: OkHttpClient.Builder by lazy { OkHttpClient.Builder() }
    private val builder = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())

    // service have not token
    override fun <S> createService(serviceClass: Class<S>): S {
        httpClient.apply {
            readTimeout(3, TimeUnit.MINUTES)
            connectTimeout(3, TimeUnit.MINUTES)
            addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .addHeader("Accept", "application/json")
                    .method(chain.request().method, chain.request().body)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
            }
        }

        val gson = GsonBuilder()
            .setLenient()
            .create()
        val client = httpClient.build()
        val retrofit = builder.baseUrl(Common.DOMAIN).client(client).addConverterFactory(
            GsonConverterFactory.create(gson)).build()
        return retrofit.create(serviceClass)
    }

    override fun <S> createServiceToken(serviceClass: Class<S>): S {
        val token = SharedPrefs.instance?.get(Common.TOKEN, String::class.java)
        httpClient.apply {
            readTimeout(3, TimeUnit.MINUTES)
            connectTimeout(3, TimeUnit.MINUTES)
            addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer " + token)
                    .method(chain.request().method, chain.request().body).build()
                chain.proceed(requestBuilder)
            }
            if (BuildConfig.DEBUG) {
                logging.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(logging)
            }
        }
        val client = httpClient.build()
        val retrofit = builder.baseUrl(Common.API).client(client).build()
        return retrofit.create(serviceClass)
    }
}