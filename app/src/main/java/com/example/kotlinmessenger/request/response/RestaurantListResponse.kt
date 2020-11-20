package com.example.kotlinmessenger.request.response

import com.example.kotlinmessenger.models.Restaurants
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RestaurantListResponse {

    @SerializedName("recipes")
    @Expose
    private val restaurants: List<Restaurants>? = null

    val getRestaurants: List<Restaurants>?
    get() = restaurants

}