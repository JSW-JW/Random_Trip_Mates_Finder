package com.example.kotlinmessenger.models

import com.google.gson.annotations.SerializedName

data class SearchResponse (
    @SerializedName("results_found") val results_found : Int,
    @SerializedName("results_start") val results_start : Int,
    @SerializedName("results_shown") val results_shown : Int,
    @SerializedName("restaurants") val searchResponse : List<RestaurantWrapper>
)