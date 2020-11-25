package com.example.kotlinmessenger.repositories

import android.app.Service
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.kotlinmessenger.models.RestaurantDetail
import com.example.kotlinmessenger.models.RestaurantSummary
import com.example.kotlinmessenger.models.RestaurantWrapper
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
                Log.d(TAG, "loadFromDb: called")
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

    fun searchRestaurant(query: String?): LiveData<Resource<List<RestaurantSummary>?>?> {
        return object: NetworkBoundResource<List<RestaurantSummary>, RestaurantListResponse>() {
            override fun saveCallResult(item: RestaurantListResponse) {
                if(item.getRestaurants != null) {
                    for(restaurantWrapper in item.getRestaurants as List<RestaurantWrapper>) {
                        restaurantDao?.insertRestaurants(restaurantWrapper.restaurant)
                    }
                }
                Log.d(TAG, "saveCallResult: called")
            }

            override fun shouldFetch(data: List<RestaurantSummary>?): Boolean {
                return true
            }

            override fun loadFromDb(): LiveData<List<RestaurantSummary>?> {
                Log.d(TAG, "loadFromDb: called")
                return restaurantDao!!.searchRestaurant(query)
            }

            override fun createCall(): LiveData<ApiResponse<RestaurantListResponse?>?> {
                val results = ServiceGenerator.retrofitService.searchRestaurant(query)
                Log.d(TAG, "createCall: $results")
                return results
            }
        }.asLiveData
    }

}