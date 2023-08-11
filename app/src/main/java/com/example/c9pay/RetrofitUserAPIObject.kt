package com.example.c9pay

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUserAPIObject {
    companion object {
        // API base URL
        private const val BASE_URL = "http://api.teamcloudnine.link/user-service"

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val create: RetrofitUserAPIInterface = retrofit.create(RetrofitUserAPIInterface::class.java)
    }
}