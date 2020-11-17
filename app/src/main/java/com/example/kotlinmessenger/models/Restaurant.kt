package com.example.kotlinmessenger.models

import com.google.gson.annotations.SerializedName

data class Restaurant(
    @SerializedName("R") val r: R = R(),
    @SerializedName("id") val id: String = "",
    @SerializedName("name") val name: String = "",
    @SerializedName("location") val location: Location = Location(),
    @SerializedName("cuisines") val cuisines: String = "",
    @SerializedName("timings") val timings: String = "",
    @SerializedName("average_cost_for_two") val average_cost_for_two: String = "",
    @SerializedName("price_range") val price_range: String = "",
    @SerializedName("currency") val currency: String= "",
    @SerializedName("highlights") val highlights: List<String> = ArrayList(),
    @SerializedName("offers") val offers: List<String> = ArrayList(),
    /*@SerializedName("user_rating") val user_rating: User_rating = User_rating(),*/
    @SerializedName("all_reviews_count") val all_reviews_count: String = "",
    @SerializedName("featured_image") val featured_image: String = "",
    @SerializedName("is_table_reservation_supported") val is_table_reservation_supported: String = "",
    @SerializedName("phone_numbers") val phone_numbers: String = ""
) {
    data class R(
        @SerializedName("res_id") val res_id: Int = 0,
        @SerializedName("is_grocery_store") val is_grocery_store: Boolean = false,
        @SerializedName("has_menu_status") val has_menu_status: Has_menu_status = Has_menu_status()
    )

    data class Location(
        @SerializedName("address") val address: String = "",
        @SerializedName("locality") val locality: String = "",
        @SerializedName("city") val city: String = "",
        @SerializedName("city_id") val city_id : Int = 0,
        @SerializedName("latitude") val latitude: Double = 0.0,
        @SerializedName("longitude") val longitude: Double = 0.0,
        @SerializedName("zipcode") val zipcode: String = "",
        @SerializedName("country_id") val country_id: String = ""
    )

    data class Has_menu_status(
        @SerializedName("delivery") val delivery: Int = 0,
        @SerializedName("takeaway") val takeaway: Int = 0
    )


    data class Bg_color(
        @SerializedName("type") val type: String = "",
        @SerializedName("tint") val tint: String = ""
    )

    data class Title(
        @SerializedName("text") val text: Double = 0.0
    )

    data class User_rating(
        @SerializedName("aggregate_rating") val aggregate_rating: Double = 0.0,
        @SerializedName("rating_text") val rating_text: String = "",
        @SerializedName("rating_color") val rating_color: String = "",
        @SerializedName("rating_obj") val rating_obj: Rating_obj = Rating_obj(),
        @SerializedName("votes") val votes: String = ""
    )

    data class Rating_obj(
        @SerializedName("title") val title: Title = Title(),
        @SerializedName("bg_color") val bg_color: Bg_color = Bg_color()
    )

}