package com.example.todo

import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST

interface apiinterfaceservive {

    @POST("api-token-auth/")
    fun send(
        @Body token:getTokener
    ):Call<tokenresponse>


    @GET("getlist/")
    fun  getworklist():Call<worklistdata>

//    @Headers(
//        "Content-Type: application/json"
//    )
    @POST("signup/")
    fun registering(
        @Body cred : MultipartBody
    ):Call<signinresp>

    @POST("create/")
    fun creatework(
        @Body make : MultipartBody
    ):Call<workdata>
}