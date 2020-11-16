package com.example.kotlinmessenger.request

import com.example.kotlinmessenger.models.Restaurant
import com.example.kotlinmessenger.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    //   /search?category=3
    @Headers("X-Zomato-API-Key", Constants.API_KEY)
    @GET("/search")
    fun searchRecipe(
        @Query("category") category: Int?
    ): Call<Restaurant>
}