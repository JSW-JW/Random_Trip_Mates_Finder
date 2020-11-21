package com.example.kotlinmessenger.request.response

import com.example.kotlinmessenger.models.RestaurantWrapper
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RestaurantListResponse {

    @SerializedName("restaurants")
    @Expose
    private val restaurants: List<RestaurantWrapper>? = null

    val getRestaurants: List<RestaurantWrapper>?
    get() = restaurants

}