package com.example.c9pay.retrofit.userservice.dto

data class LoginResponseOK (
    val userToken: String,
    val userRedirectURL: String
): LoginResponse