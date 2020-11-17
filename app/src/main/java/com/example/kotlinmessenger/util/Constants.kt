package com.example.kotlinmessenger.util

class Constants {

    companion object {
        const val HEADER_KEY = "X-Zomato-API-Key"
        const val API_KEY = "46cb92b514f86a231fbedce8bba88783"
        const val BASE_URL = "https://developers.zomato.com/api/v2.1/"
        val DEFAULT_SEARCH_CATEGORIES = arrayOf(
            "Breakfast",
            "Lunch",
            "Dinner",
            "Pubs & Bars",
            "Takeaway",
            "Cafes"
        )

        val DEFAULT_SEARCH_CATEGORIES_ID = arrayOf(
            8,
            9,
            10,
            11,
            5,
            6
        )  // not in order because only some of the api list data has been selected.

        val DEFAULT_SEARCH_CATEGORY_IMAGES = arrayOf(
            "breakfast",
            "lunch",
            "dinner",
            "pubs_bars",
            "takeaway",
            "cafes"
        )

    }

}