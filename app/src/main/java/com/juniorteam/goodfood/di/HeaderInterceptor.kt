package com.juniorteam.goodfood.di

import android.util.Log
import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

class HeaderInterceptor : Interceptor {

    private val tag = HeaderInterceptor::class.java.simpleName

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestBuilder = request.newBuilder()
        var body: ResponseBody?
        var headers: Headers?

        chain.proceed(requestBuilder.build()).use {
            body = it.body()
            headers = it.headers()
        }
        Log.e(tag, "body = $body\nheaders = $headers")

        return chain.proceed(requestBuilder.build())
    }
}