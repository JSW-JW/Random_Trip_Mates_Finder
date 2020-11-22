package com.example.kotlinmessenger.persistence

import androidx.room.TypeConverter
import com.example.kotlinmessenger.models.RestaurantSummary
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {
    @TypeConverter
    fun fromString(value: String?): Array<RestaurantSummary> {
        val listType =
            object : TypeToken<Array<RestaurantSummary>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: Array<RestaurantSummary>): String {
        val gson = Gson()
        return gson.toJson(list)
    }

}

/*
object Converters {

    @TypeConverter
    fun listToJson(value: List<RestaurantSummary>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<RestaurantSummary> {
        return Gson().fromJson(value, Array<RestaurantSummary>::class.java).toList()
    }
}*/
