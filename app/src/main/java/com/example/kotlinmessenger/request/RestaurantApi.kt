package com.example.kotlinmessenger.request

import androidx.lifecycle.LiveData
import com.example.kotlinmessenger.request.response.ApiResponse
import com.example.kotlinmessenger.request.response.RestaurantListResponse
import com.example.kotlinmessenger.request.response.RestaurantResponse
import com.example.kotlinmessenger.util.Constants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RestaurantApi {

    //   /search?category=3
    @Headers("${Constants.HEADER_KEY}: ${Constants.API_KEY}")
    @GET("search")
    fun searchByCategoryId(
        @Query("category") category: Int?
    ): LiveData<ApiResponse<RestaurantListResponse?>?>

    @Headers("${Constants.HEADER_KEY}: ${Constants.API_KEY}")
    @GET("search")
    fun searchByCity(
        @Query("entity_id") cityName: String,
        @Query("entity_type") entityType: String
    ): LiveData<ApiResponse<RestaurantResponse>>
}