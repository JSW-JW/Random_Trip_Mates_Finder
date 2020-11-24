package com.example.kotlinmessenger.request.response

import com.example.kotlinmessenger.models.RestaurantDetail

class RestaurantResponse {

    private val restaurant: RestaurantDetail? = null

    val getRestaurant: RestaurantDetail?
        get() = restaurant

}