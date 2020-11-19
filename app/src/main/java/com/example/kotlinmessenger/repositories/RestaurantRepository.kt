package com.example.kotlinmessenger.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.kotlinmessenger.models.Restaurant
import com.example.kotlinmessenger.request.response.ApiResponse
import com.example.kotlinmessenger.request.response.RestaurantListResponse
import com.example.kotlinmessenger.util.Resource

class RestaurantRepository(context: Context) {

    private var instance: RestaurantRepository? = null

    fun instance(context: Context): RestaurantRepository {
        if(instance == null) {
            instance = RestaurantRepository(context)
        }
        return instance as RestaurantRepository
    }

    fun searchByCategory(): LiveData<Resource<List<Restaurant>?>?>? {
        return object: NetworkBoundResource<List<Restaurant>, RestaurantListResponse>() {
            override fun saveCallResult(item: RestaurantListResponse) {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<Restaurant>?): Boolean {
                TODO("Not yet implemented")
            }

            override fun loadFromDb(): LiveData<List<Restaurant>?> {
                TODO("Not yet implemented")
            }

            override fun createCall(): LiveData<ApiResponse<RestaurantListResponse?>?> {
                TODO("Not yet implemented")
            }

        }.asLiveData
    }

    fun searchByCity(): LiveData<Resource<List<Restaurant>?>?>? {
        return object: NetworkBoundResource<List<Restaurant>, RestaurantListResponse>() {
            override fun saveCallResult(item: RestaurantListResponse) {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<Restaurant>?): Boolean {
                TODO("Not yet implemented")
            }

            override fun loadFromDb(): LiveData<List<Restaurant>?> {
                TODO("Not yet implemented")
            }

            override fun createCall(): LiveData<ApiResponse<RestaurantListResponse?>?> {
                TODO("Not yet implemented")
            }

        }.asLiveData
    }

    fun searchRestaurantApi(){

    }

}