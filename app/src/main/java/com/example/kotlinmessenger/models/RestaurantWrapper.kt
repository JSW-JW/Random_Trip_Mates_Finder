package com.example.kotlinmessenger.models

import com.google.gson.annotations.SerializedName

data class RestaurantWrapper (

    @SerializedName("restaurant") val restaurant : RestaurantSummary
)