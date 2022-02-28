package com.hsj.rxtest.retrofit.interceptor

import com.hsj.rxtest.retrofit.Headers
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.Response

/**
 * multipart body에 대한 content-type(boundary) 자동 추가
 */
abstract class AppInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val builder = request.newBuilder()
        authorization?.let {
            builder.addHeader(Headers.AUTHORIZATION, it)
        }
        userAgent?.let {
            builder.addHeader(Headers.USER_AGENT, it)
        }
        when (val body = chain.request().body()) {
            is MultipartBody -> builder.addHeader(
                Headers.CONTENT_TYPE, "multipart/form-data; boundary=${body.boundary()}"
            )
        }
        return chain.proceed(builder.build())
    }

    abstract val authorization: String?
    abstract val userAgent: String?

}