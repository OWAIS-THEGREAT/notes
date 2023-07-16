package com.example.todo

import okhttp3.Interceptor
import okhttp3.Response

class intercepter(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()


        val modifiedRequest = request.newBuilder()
            .header("Authorization", "Token $token")
            .build()

        return chain.proceed(modifiedRequest)
    }

}