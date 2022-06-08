package com.example.foodrecipe.util

import com.example.foodrecipe.BuildConfig
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request()
            .url
            .newBuilder()
            .build()
        val request: Request = chain.request()
            .newBuilder()
            .addHeader("X-RapidAPI-Host",BuildConfig.X_RapidAPI_Host)
            .addHeader("X-RapidAPI-Key",BuildConfig.X_RapidAPI_Key)
            .url(url)
            .build()
        return chain.proceed(request)
    }
}