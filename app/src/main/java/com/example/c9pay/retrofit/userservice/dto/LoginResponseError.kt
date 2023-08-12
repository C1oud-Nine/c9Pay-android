package com.example.c9pay.retrofit.userservice.dto

data class LoginResponseError (
    val code: Int,
    val message: String,
    val method: String,
    val url: String
): LoginResponse