package com.example.c9pay.retrofit.userservice

import okhttp3.ResponseBody

class ErrorUtility {
    companion object {
        inline fun <reified T> getErrorResponse(errorBody: ResponseBody): T? {
            return RetrofitService.retrofit.responseBodyConverter<T>(
                T::class.java,
                T::class.java.annotations
            ).convert(errorBody)
        }
    }
}