package com.hsj.rxtest.retrofit.base

import com.hsj.rxtest.retrofit.interceptor.AppInterceptor
import com.hsj.rxtest.retrofit.interceptor.NetInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseProxy(connectSeconds: Long = DEFAULT_CONNECT_SECONDS,
                     writeSeconds: Long = DEFAULT_WRITE_SECONDS,
                     readSeconds: Long = DEFAULT_READ_SECONDS
) : Proxy(connectSeconds, writeSeconds, readSeconds) {
    override fun onCreateOkHttpClient(builder: OkHttpClient.Builder): OkHttpClient {
        builder.addInterceptor(appInterceptor)
        builder.addNetworkInterceptor(netInterceptor)
        return super.onCreateOkHttpClient(builder)
    }

    override fun onCreateRetrofit(builder: Retrofit.Builder): Retrofit {
        builder.addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }

    protected fun howserUrl(path: String): String {
        return "https/$path"
    }


    companion object {
        private const val TYPE_DELIVERY = "GOODS"
        private const val TYPE_MEASURE = "MEASURE"
        private const val TYPE_SPOT = "SPOT"

        /**
         * App -> Server Request Header 정의
         */
        val appInterceptor: AppInterceptor = object : AppInterceptor() {
            override val authorization: String?
                get() = TODO("Not yet implemented")
            override val userAgent: String?
                get() = TODO("Not yet implemented")
        }

        /**
         * Server -> App Response Header 정의
         */
        val netInterceptor: NetInterceptor = object : NetInterceptor() {
            override fun onAuthorizationUpdated(newToken: String) {
//                if (Pref.accessToken != newToken)
//                    Pref.accessToken = newToken
            }
        }
    }
}