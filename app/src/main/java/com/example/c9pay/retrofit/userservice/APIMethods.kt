package com.example.c9pay.retrofit.userservice

import com.example.c9pay.retrofit.userservice.dto.LoginRequest
import com.example.c9pay.retrofit.userservice.dto.LoginResponse
import com.example.c9pay.retrofit.userservice.dto.LoginResponseError
import com.example.c9pay.retrofit.userservice.dto.LoginResponseOK

class APIMethods {
    companion object {
        fun postLogin(
            loginRequest: LoginRequest,
            callback: (response: LoginResponse?) -> Unit
        ) {
            RetrofitService.UserServiceInterface.postLogin(
                loginRequest
            ).enqueue(
                MethodCallback.generalCallback<LoginResponse, LoginResponseOK, LoginResponseError>(
                    callback
                )
            )
        }
    }
}