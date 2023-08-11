package com.example.c9pay

import com.google.gson.annotations.SerializedName

data class BodySignupRequest (
    @SerializedName("userId")
    val userId: String?,

    @SerializedName("username")
    val username: String?,

    @SerializedName("password")
    val password: String?,

    @SerializedName("email")
    val email: String?
)