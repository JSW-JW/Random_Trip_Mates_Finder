package com.example.kotlinmessenger.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kotlinmessenger.models.RestaurantDetail
import com.example.kotlinmessenger.models.RestaurantSummary

@Database(entities = [RestaurantSummary::class], version = 1)
@TypeConverters(Converters::class)
abstract class RestaurantDatabase : RoomDatabase() {

    private val mRestaurantDao: RestaurantDao? = null

    companion object {
        const val DATABASE_NAME = "restaurants_db"
        private var instance: RestaurantDatabase? = null
        fun getInstance(context: Context): RestaurantDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RestaurantDatabase::class.java,
                    DATABASE_NAME
                ).build()

            }
            return instance
        }
    }

    abstract fun restaurantDao(): RestaurantDao

}
