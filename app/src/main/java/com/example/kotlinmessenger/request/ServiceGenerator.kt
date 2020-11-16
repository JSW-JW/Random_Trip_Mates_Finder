package com.example.kotlinmessenger.request

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceGenerator() {

    private val retrofitBuilder: Retrofit.Builder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://developers.zomato.com/api/v2.1")

    private val retrofit = retrofitBuilder.build()

    private var apiService = retrofit.create(ApiService::class.java)

    val restaurantApi: ApiService
        get() {
            return apiService
        }
}