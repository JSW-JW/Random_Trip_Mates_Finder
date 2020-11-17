package com.example.kotlinmessenger.request

import com.example.kotlinmessenger.models.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    //   /search?category=3
    @Headers("X-Zomato-API-Key: 46cb92b514f86a231fbedce8bba88783")
    @GET("search")
    fun searchWithCategory(
        @Query("category") category: Int?
    ): Call<Response>
}