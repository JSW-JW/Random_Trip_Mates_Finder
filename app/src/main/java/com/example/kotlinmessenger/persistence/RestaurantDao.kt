package com.example.kotlinmessenger.persistence

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kotlinmessenger.models.Restaurant

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // not replace the object with new one.
    fun insertRecipes(vararg restaurants: Restaurant?): LongArray?

    @Insert(onConflict = OnConflictStrategy.REPLACE) // replace the object with new one.
    fun insertRecipe(restaurant: Restaurant?)

    @Update
    fun updateRestaurants(vararg restaurant: Restaurant) // TODO: search how to access particular class and field(if embedded annotation, cannot access the class itself.

    @Query("SELECT * FROM restaurants WHERE city LIKE '%' || :query || '%'")
    fun searchWithCities(
        query: String?,
        pageNumber: Int
    ): LiveData<List<Restaurant?>?>?

    @Query("SELECT * FROM restaurants WHERE res_id = :restaurant_id")
    fun getRestaurants(restaurant_id: String?): LiveData<Restaurant?>?

}