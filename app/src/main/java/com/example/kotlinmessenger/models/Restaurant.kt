package com.example.kotlinmessenger.models

import com.example.kotlinmessenger.R

class Restaurant(
    val name: String = "",
    val timing: String = "",
    val category: String = "",
    val featured_image: String = "",
    val resource: R = R(),
    val location: Location = Location()
) {
    constructor() : this("", "", "","", R(), Location())

    class R(
        val res_id: String,
        val is_grocery_store: String
    ) {
        constructor(): this("","")
    }

    class Location(
        val address: String,
        val locality: String,
        val latitude: Float,
        val longitude: Float,
        val city: String,
        val city_id: String
    ) {
    constructor(): this("", "", 0f, 0f,"","")
    }

}