package com.example.kotlinmessenger.util

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.request.response.ApiResponse
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Type

class LiveDataCallAdapter<R>(private val responseType: Type) :
    CallAdapter<R, LiveData<ApiResponse<R>>> {

    companion object {
        private const val TAG = "LiveDataCallAdapter"
    }

    override fun responseType(): Type {
        return responseType
    }

    override fun adapt(call: Call<R>): LiveData<ApiResponse<R>> {
        return object: LiveData<ApiResponse<R>>() {
            override fun onActive() {
                super.onActive()
                Log.d(TAG, "onActive: Called")
                call.enqueue(object: Callback<R> {
                    override fun onResponse(call: Call<R>, response: Response<R>) {
                        Log.d(TAG, "onResponse: ${response.body()}")
                        postValue(ApiResponse<R>().create(response))
                    }

                    override fun onFailure(call: Call<R>, t: Throwable) {
                        Log.d(TAG, "onFailure: $t")
                        postValue(ApiResponse<R>().create(t))
                    }
                })
            }
        }
    }

}
