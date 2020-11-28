package com.example.kotlinmessenger.request

import androidx.lifecycle.LiveData
import com.example.kotlinmessenger.models.RestaurantDetail
import com.example.kotlinmessenger.request.response.ApiResponse
import com.example.kotlinmessenger.request.response.RestaurantListResponse
import com.example.kotlinmessenger.request.response.RestaurantResponse
import com.example.kotlinmessenger.util.Constants
import retrofit2.Call
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
    @GET("restaurant")
    fun searchByRestaurantId(
        @Query("res_id") resId: Int
    ): LiveData<ApiResponse<RestaurantDetail?>?>

    @Headers("${Constants.HEADER_KEY}: ${Constants.API_KEY}")
    @GET("search")
    fun searchRestaurant(
        @Query("q") query: String?
    ): LiveData<ApiResponse<RestaurantListResponse?>?>


    @Headers("${Constants.HEADER_KEY}: ${Constants.API_KEY}")
    @GET("restaurant")
    fun testApiCall(
        @Query("res_id") resId: Int
    ): Call<RestaurantResponse>
}
