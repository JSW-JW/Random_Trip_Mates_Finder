package com.example.kotlinmessenger.persistence

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters2 {
    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): Array<String> {
        val listType =
            object : TypeToken<Array<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: Array<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}