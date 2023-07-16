package com.example.todo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitobjectup {

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl("http://10.0.2.2:8000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiinterface: apiinterfaceservive by lazy {
        retrofit.create(apiinterfaceservive::class.java)
    }
}