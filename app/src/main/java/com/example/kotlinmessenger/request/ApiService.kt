package com.example.kotlinmessenger.request

import com.example.kotlinmessenger.models.Response
import com.example.kotlinmessenger.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    //   /search?category=3
    @Headers("${Constants.HEADER_KEY}: ${Constants.API_KEY}")
    @GET("search")
    fun searchWithCategory(
        @Query("category") category: Int?
    ): Call<Response>
}