package com.hsj.rxtest.retrofit.interceptor

import com.hsj.rxtest.retrofit.Headers
import okhttp3.Interceptor
import okhttp3.Response

abstract class NetInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        response.header(Headers.AUTHORIZATION)?.let { newToken ->
            onAuthorizationUpdated(newToken)
        }
        return response
    }

    abstract fun onAuthorizationUpdated(newToken: String)
}