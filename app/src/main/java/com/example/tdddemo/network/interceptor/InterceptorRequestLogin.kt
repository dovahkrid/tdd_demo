package com.example.tdddemo.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class InterceptorRequestLogin : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .method(original.method, original.body)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}