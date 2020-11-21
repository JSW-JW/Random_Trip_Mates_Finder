package com.example.kotlinmessenger.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.kotlinmessenger.models.RestaurantSummary
import com.example.kotlinmessenger.persistence.RestaurantDao
import com.example.kotlinmessenger.request.ServiceGenerator
import com.example.kotlinmessenger.request.response.ApiResponse
import com.example.kotlinmessenger.request.response.RestaurantListResponse
import com.example.kotlinmessenger.util.Resource

class RestaurantRepository(context: Context, val restaurantDao: RestaurantDao) {

    private var instance: RestaurantRepository? = null

    fun instance(context: Context): RestaurantRepository {
        if (instance == null) {
            instance = RestaurantRepository(context, restaurantDao)
        }
        return instance as RestaurantRepository
    }

    fun searchByCategoryId(category_id: Int): LiveData<Resource<List<RestaurantSummary>?>?>? {
        return object : NetworkBoundResource<List<RestaurantSummary>, RestaurantListResponse>() {
            override fun saveCallResult(item: RestaurantListResponse) {
                if (item.getRestaurants != null) {
                    val recipes: Array<RestaurantSummary> = emptyArray()
                    for ((i, restaurantWrapper) in item.getRestaurants!!.withIndex()) {
                        restaurantWrapper.restaurant.category_id = category_id
                        recipes?.set(i, restaurantWrapper.restaurant)
                    }

                    restaurantDao.insertRestaurants(recipes)
                }
            }

            override fun shouldFetch(data: List<RestaurantSummary>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<RestaurantSummary>?> {
                return restaurantDao.searchByCategoryId(category_id)
            }

            override fun createCall(): LiveData<ApiResponse<RestaurantListResponse?>?> {
                return ServiceGenerator.retrofitService.searchByCategoryId(category_id)
            }

        }.asLiveData
    }

    fun searchByCity(): LiveData<Resource<List<RestaurantSummary>?>?>? {
        return object : NetworkBoundResource<List<RestaurantSummary>, RestaurantListResponse>() {
            override fun saveCallResult(item: RestaurantListResponse) {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: List<RestaurantSummary>?): Boolean {
                TODO("Not yet implemented")
            }

            override fun loadFromDb(): LiveData<List<RestaurantSummary>?> {
                TODO("Not yet implemented")
            }

            override fun createCall(): LiveData<ApiResponse<RestaurantListResponse?>?> {
                TODO("Not yet implemented")
            }

        }.asLiveData
    }

    fun searchRestaurantById() {

    }

}