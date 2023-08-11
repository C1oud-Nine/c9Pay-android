package com.example.c9pay

import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.http.Body


interface RetrofitUserAPIInterface {
    @Headers("accept: application/json", "content-type: application/json")

    @POST("/api/user/signup")
    fun postSignup (@Body bodySignupRequest: BodySignupRequest?): Call<BodySignupRequest?>?

    @POST("/api/login")
    fun postLogin (@Body bodyLoginRequest: BodyLoginRequest?): Call<BodyLoginRequest?>?

    @GET("/api/user")
    fun getUser()

    @GET("/api/qr")
    fun getQR()

}