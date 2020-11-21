package com.example.kotlinmessenger.request

import com.example.kotlinmessenger.util.Constants
import com.example.kotlinmessenger.util.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .build()

        val retrofitService: RestaurantApi by lazy {
            retrofit.create(RestaurantApi::class.java)
        }
}