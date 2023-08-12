package com.example.c9pay.retrofit.userservice

import okhttp3.JavaNetCookieJar
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager

class RetrofitService {
    companion object {
        // User API Base URL
        var baseUrl = "http://api.teamcloudnine.link/user-service/"

        var builder = OkHttpClient().newBuilder()

        var okHttpClient = builder
            .cookieJar(JavaNetCookieJar(CookieManager()))
            .build()

        var retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(this.baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var UserServiceInterface: RetrofitInterface = retrofit.create(RetrofitInterface::class.java)
    }
}