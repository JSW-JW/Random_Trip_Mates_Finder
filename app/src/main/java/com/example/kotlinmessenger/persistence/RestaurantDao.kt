package com.example.kotlinmessenger.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlinmessenger.models.RestaurantDetail
import com.example.kotlinmessenger.models.RestaurantSummary

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurants(vararg restaurants: RestaurantSummary?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRestaurant(restaurant: RestaurantDetail)

    @Update
    fun updateRestaurant(restaurant: RestaurantSummary) // TODO: search how to access particular class and field(if embedded annotation, cannot access the class itself.

    @Query(
        "SELECT * FROM restaurants WHERE name LIKE '%' || :query || '%' OR address LIKE '%' || :query || '%' OR locality LIKE '%' || :query || '%' OR city LIKE '%' || :query || '%'")
    fun searchRestaurant(
        query: String?
    ): LiveData<List<RestaurantSummary>?>

    @Query("SELECT * FROM restaurants WHERE category_id=:category_id")
    fun searchByCategoryId(category_id: Int): LiveData<List<RestaurantSummary>?>

    @Query("SELECT * FROM restaurant WHERE res_id=:resId")
    fun searchByRestaurantId(resId: Int): LiveData<RestaurantDetail?>

   /* @Query("SELECT * FROM restaurant WHERE res_id = :restaurant_id")
    fun getRestaurant(restaurant_id: String): LiveData<RestaurantDetail?>?*/


}