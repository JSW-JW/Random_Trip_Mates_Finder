package com.example.kotlinmessenger.request.response

import android.util.Log
import retrofit2.Response
import java.io.IOException

open class ApiResponse<T> {

    companion object {
        private const val TAG = "ApiResponse"
    }

    fun create(error: Throwable): ApiResponse<T> {
        return ApiErrorResponse<T>(if (error.message != "") error.message else "Unknown error\nCheck network connection")

    }

    fun create(response: Response<T>): ApiResponse<T> {
        return if (response.isSuccessful) {
            val body = response.body()
            if (body == null || response.code() == 204) { // 204 is empty response code.
                Log.d(TAG, "create: ApiEmptyResponse")
                ApiEmptyResponse<T>()
            } else {
                ApiSuccessResponse<T>(response.body() as T)
            }
        } else {
            var errorMessage: String? = ""
            errorMessage = try {
                response.errorBody()!!.string()
            } catch (e: IOException) {
                e.printStackTrace()
                response.message()
            }
            ApiErrorResponse(errorMessage)
        }
    }

    inner class ApiSuccessResponse<T> internal constructor(val body: T) : ApiResponse<T>()

    inner class ApiErrorResponse<T> internal constructor(val errorMessage: String?) : ApiResponse<T>()

    inner class ApiEmptyResponse<T> : ApiResponse<T>()

}
