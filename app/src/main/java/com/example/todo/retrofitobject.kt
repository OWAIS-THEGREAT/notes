package com.example.todo

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitobject {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createApiService(token: String): apiinterfaceservive {
        val interceptor = intercepter(token)

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = retrofit.newBuilder()
            .client(client)
            .build()

        return retrofit.create(apiinterfaceservive::class.java)
    }

    val apiinterface: apiinterfaceservive by lazy {
        retrofit.create(apiinterfaceservive::class.java)
    }
}