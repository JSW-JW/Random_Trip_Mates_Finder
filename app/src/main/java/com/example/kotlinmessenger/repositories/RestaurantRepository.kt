package com.example.kotlinmessenger.repositories

import android.content.Context

class RestaurantRepository(context: Context) {

    private var instance: RestaurantRepository? = null

    fun instance(context: Context): RestaurantRepository {
        if(instance == null) {
            instance = RestaurantRepository(context)
        }
        return instance as RestaurantRepository
    }

    fun searchRestaurantsApi(){

    }

}