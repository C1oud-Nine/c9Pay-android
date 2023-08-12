package com.example.c9pay.retrofit.userservice

import com.example.c9pay.retrofit.userservice.dto.LoginRequest
import com.example.c9pay.retrofit.userservice.dto.LoginResponseOK
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RetrofitInterface {
    @Headers("Content-Type: application/json")
    @POST("api/login")
    fun postLogin(
        @Body loginRequest: LoginRequest
    ): Call<LoginResponseOK>
}