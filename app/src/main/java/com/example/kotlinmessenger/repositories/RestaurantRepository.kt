package com.example.kotlinmessenger.repositories

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.kotlinmessenger.models.RestaurantDetail
import com.example.kotlinmessenger.models.RestaurantSummary
import com.example.kotlinmessenger.persistence.RestaurantDao
import com.example.kotlinmessenger.persistence.RestaurantDatabase
import com.example.kotlinmessenger.request.ServiceGenerator
import com.example.kotlinmessenger.request.response.ApiResponse
import com.example.kotlinmessenger.request.response.RestaurantListResponse
import com.example.kotlinmessenger.request.response.RestaurantResponse
import com.example.kotlinmessenger.util.Resource

class RestaurantRepository(val context: Context) {

    var restaurantDao: RestaurantDao? = null

    val TAG = "RestaurantRepository"

    init {
        restaurantDao = RestaurantDatabase.getInstance(context)?.restaurantDao()
        Log.d(TAG, ": $restaurantDao")
    }

    companion object {
        var instance: RestaurantRepository? = null
        fun instance(context: Context): RestaurantRepository {
            if (instance == null) {
                instance = RestaurantRepository(context)
            }
            return instance as RestaurantRepository
        }
    }


    fun searchByCategoryId(category_id: Int): LiveData<Resource<List<RestaurantSummary>?>?> {
        return object : NetworkBoundResource<List<RestaurantSummary>, RestaurantListResponse>() {
            override fun saveCallResult(item: RestaurantListResponse) {
                if (item.getRestaurants != null) {
                    val restaurantArray: Array<RestaurantSummary?> = arrayOfNulls(item.getRestaurants!!.size)
                    /*for ((i, restaurantWrapper) in item.getRestaurants!!.withIndex()) {
                        restaurantWrapper.restaurant.category_id = category_id
                        restaurantArray[i] = restaurantWrapper.restaurant
                        Log.d(TAG, "saveCallResult: $restaurantArray")
                    }*/
                    item.getRestaurants!!.forEachIndexed { index, restaurantWrapper ->
                        restaurantWrapper.restaurant.category_id = category_id
                        restaurantArray[index] = restaurantWrapper.restaurant
                    }
                    val restaurants: Array<out RestaurantSummary?> = restaurantArray
                    restaurantDao!!.insertRestaurants(*restaurants)  // using spread operator
                }

            }

            override fun shouldFetch(data: List<RestaurantSummary>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<RestaurantSummary>?> {
                return restaurantDao!!.searchByCategoryId(category_id)
            }

            override fun createCall(): LiveData<ApiResponse<RestaurantListResponse?>?> {
                return ServiceGenerator.retrofitService.searchByCategoryId(category_id)
            }

        }.asLiveData
    }

    fun searchByCity(): LiveData<Resource<List<RestaurantSummary>?>?> {
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

    fun searchByRestaurantId(resId: Int): LiveData<Resource<RestaurantDetail?>?> {
        return object: NetworkBoundResource<RestaurantDetail, RestaurantResponse>() {
            override fun saveCallResult(item: RestaurantResponse) {
                Log.d(TAG, "saveCallResult: called")
                item.getRestaurant?.let { restaurantDetail ->
                    Log.d(TAG, "saveCallResult: $restaurantDetail")
                    restaurantDao!!.insertRestaurant(restaurantDetail)
                }
            }

            override fun shouldFetch(data: RestaurantDetail?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<RestaurantDetail?> {
                return restaurantDao!!.searchByRestaurantId(resId)
            }

            override fun createCall(): LiveData<ApiResponse<RestaurantResponse?>?> {
                Log.d(TAG, "createCall: called")
                val apiResponse = ServiceGenerator.retrofitService.searchByRestaurantId(resId)
                Log.d(TAG, "createCall: $apiResponse")
                return apiResponse
            }

        }.asLiveData
    }

}