package com.example.kotlinmessenger.request

import com.example.kotlinmessenger.models.SearchResponse
import com.example.kotlinmessenger.util.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestaurantApi {

    //   /search?category=3
    @Headers("${Constants.HEADER_KEY}: ${Constants.API_KEY}")
    @GET("search")
    fun searchByCategory(
        @Query("category") category: Int?
    ): Call<SearchResponse>

    @Headers("${Constants.HEADER_KEY}: ${Constants.API_KEY}")
    @GET("search")
    fun searchByCity(
        @Query("entity_id") cityId: Int,
        @Query("entity_type") entityType: String
    ): Call<SearchResponse>
}