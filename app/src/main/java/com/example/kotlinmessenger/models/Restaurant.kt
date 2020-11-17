package com.example.kotlinmessenger.models

import com.google.gson.annotations.SerializedName

data class Restaurant (

    @SerializedName("R") val r : R,
    @SerializedName("apikey") val apiapikey : String,
    @SerializedName("id") val id : String,
    @SerializedName("name") val name : String,
    @SerializedName("url") val url : String,
    @SerializedName("location") val location : Location,
    @SerializedName("switch_to_order_menu") val switch_to_order_menu : String,
    @SerializedName("cuisines") val cuisines : String,
    @SerializedName("timings") val timings : String,
    @SerializedName("average_cost_for_two") val average_cost_for_two : String,
    @SerializedName("price_range") val price_range : String,
    @SerializedName("currency") val currency : String,
    @SerializedName("highlights") val highlights : List<String>,
    @SerializedName("offers") val offers : List<String>,
    @SerializedName("opentable_support") val opentable_support : String,
    @SerializedName("is_zomato_book_res") val is_zomato_book_res : String,
    @SerializedName("mezzo_provider") val mezzo_provider : String,
    @SerializedName("is_book_form_web_view") val is_book_form_web_view : String,
    @SerializedName("book_form_web_view_url") val book_form_web_view_url : String,
    @SerializedName("book_again_url") val book_again_url : String,
    @SerializedName("thumb") val thumb : String,
    @SerializedName("user_rating") val user_rating : User_rating,
    @SerializedName("all_reviews_count") val all_reviews_count : String,
    @SerializedName("photos_url") val photos_url : String,
    @SerializedName("photo_count") val photo_count : String,
    @SerializedName("menu_url") val menu_url : String,
    @SerializedName("featured_image") val featured_image : String,
    @SerializedName("has_online_delivery") val has_online_delivery : String,
    @SerializedName("is_delivering_now") val is_delivering_now : String,
    @SerializedName("store_type") val store_type : String,
    @SerializedName("include_bogo_offers") val include_bogo_offers : Boolean,
    @SerializedName("deeplink") val deeplink : String,
    @SerializedName("is_table_reservation_supported") val is_table_reservation_supported : String,
    @SerializedName("has_table_booking") val has_table_booking : String,
    @SerializedName("events_url") val events_url : String,
    @SerializedName("phone_numbers") val phone_numbers : String,
    @SerializedName("all_reviews") val all_reviews : All_reviews,
    @SerializedName("establishment") val establishment : List<String>,
    @SerializedName("establishment_types") val establishment_types : List<String>
) {
    data class R (

        @SerializedName("res_id") val res_id : String,
        @SerializedName("is_grocery_store") val is_grocery_store : Boolean,
        @SerializedName("has_menu_status") val has_menu_status : Has_menu_status
    )

    data class Location (

        @SerializedName("address") val address : String,
        @SerializedName("locality") val locality : String,
        @SerializedName("city") val city : String,
        @SerializedName("latitude") val latitude : Double,
        @SerializedName("longitude") val longitude : Double,
        @SerializedName("zipcode") val zipcode : String,
        @SerializedName("country_id") val country_id : String
    )

    data class Has_menu_status (

        @SerializedName("delivery") val delivery : String,
        @SerializedName("takeaway") val takeaway : String
    )

    data class All_reviews (

        @SerializedName("reviews") val reviews : List<Reviews>
    )

    data class Bg_color (

        @SerializedName("type") val type : String,
        @SerializedName("tint") val tint : String
    )

    data class Reviews (

        @SerializedName("review") val review : List<String>
    )

    data class Title (

        @SerializedName("text") val text : Double
    )

    data class User (

        @SerializedName("name") val name : String,
        @SerializedName("zomato_handle") val zomato_handle : String,
        @SerializedName("foodie_level") val foodie_level : String,
        @SerializedName("foodie_level_num") val foodie_level_num : String,
        @SerializedName("foodie_color") val foodie_color : String,
        @SerializedName("profile_url") val profile_url : String,
        @SerializedName("profile_deeplink") val profile_deeplink : String,
        @SerializedName("profile_image") val profile_image : String
    )

    data class User_rating (

        @SerializedName("aggregate_rating") val aggregate_rating : Double,
        @SerializedName("rating_text") val rating_text : String,
        @SerializedName("rating_color") val rating_color : String,
        @SerializedName("rating_obj") val rating_obj : Rating_obj,
        @SerializedName("votes") val votes : String
    )

    data class Rating_obj (

        @SerializedName("title") val title : Title,
        @SerializedName("bg_color") val bg_color : Bg_color
    )

}