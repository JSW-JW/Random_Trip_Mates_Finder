package com.example.kotlinmessenger.request


import com.example.kotlinmessenger.request.response.CustomInterceptor
import com.example.kotlinmessenger.util.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .build()

        val retrofitService: RestaurantApi by lazy {
            retrofit.create(RestaurantApi::class.java)
        }
}