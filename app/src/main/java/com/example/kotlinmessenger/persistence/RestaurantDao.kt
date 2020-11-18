package com.example.kotlinmessenger.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinmessenger.models.Restaurant

@Dao
interface RestaurantDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE) // not replace the object with new one.
    fun insertRecipes(vararg restaurants: Restaurant?): LongArray?

    @Insert(onConflict = OnConflictStrategy.REPLACE) // replace the object with new one.
    fun insertRecipe(restaurant: Restaurant?)

    @Query("UPDATE restaurants SET name=:name, location=:location")
    fun updateRestaurants(
        res_id: String?,
        name: String?,
        location: Restaurant.Location
    )

    @Query("SELECT * FROM restaurants WHERE city LIKE '%' || :query || '%'")
    fun searchWithCities(
        query: String?,
        pageNumber: Int
    ): LiveData<List<Restaurant?>?>?

    @Query("SELECT * FROM restaurants WHERE res_id = :restaurant_id")
    fun getRecipe(restaurant_id: String?): LiveData<Restaurant?>?

}