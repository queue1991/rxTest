package com.hsj.rxtest.retrofit.base

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import java.util.concurrent.TimeUnit

/**
 * proxy + addCallAdapterFactory(RxJava3CallAdapterFactory.create())
 */
abstract class Proxy protected constructor(
    private val connectSeconds: Long = DEFAULT_CONNECT_SECONDS,
    private val writeSeconds: Long = DEFAULT_WRITE_SECONDS,
    private val readSeconds: Long = DEFAULT_READ_SECONDS
) {

    open fun retrofit(baseUrl: String): Retrofit {
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
                .connectTimeout(connectSeconds, TimeUnit.SECONDS)
                .writeTimeout(writeSeconds, TimeUnit.SECONDS)
                .readTimeout(readSeconds, TimeUnit.SECONDS)
        val client: OkHttpClient = onCreateOkHttpClient(clientBuilder)

        val retrofitBuilder = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(baseUrl)
        return onCreateRetrofit(retrofitBuilder)
    }

    open fun onCreateOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        return builder.build()
    }


    abstract fun onCreateRetrofit(builder: Retrofit.Builder): Retrofit

    companion object {
        const val DEFAULT_CONNECT_SECONDS = 10L
        const val DEFAULT_WRITE_SECONDS = 10L
        const val DEFAULT_READ_SECONDS = 60L
    }
}