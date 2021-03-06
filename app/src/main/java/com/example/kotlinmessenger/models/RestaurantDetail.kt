package com.example.kotlinmessenger.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurant")
data class RestaurantDetail(

    @NonNull
    @PrimaryKey(autoGenerate = true)
    val primaryKey: Int,

    @SerializedName("R")
    @Embedded val r: R = R(),

    @ColumnInfo(name = "category_id")
    @SerializedName("category_id") var category_id: Int = 0,

    @ColumnInfo(name = "id")
    @SerializedName("id") val id: String = "",

    @ColumnInfo(name = "name")
    @SerializedName("name") val name: String = "",

    @SerializedName("location")
    @Embedded val location: Location = Location(),

    @ColumnInfo(name = "cuisines")
    @SerializedName("cuisines") val cuisines: String = "",

    @ColumnInfo(name = "timings")
    @SerializedName("timings") val timings: String = "",

    @ColumnInfo(name = "average_cost_for_two")
    @SerializedName("average_cost_for_two") val average_cost_for_two: String = "",

    @ColumnInfo(name = "price_range")
    @SerializedName("price_range") val price_range: String = "",

    @ColumnInfo(name = "currency")
    @SerializedName("currency") val currency: String= "",

    @ColumnInfo(name = "highlights")
    @SerializedName("highlights") val highlights: Array<String> = emptyArray(),

    @ColumnInfo(name = "offers")
    @SerializedName("offers") val offers: Array<String> = emptyArray(),

    @SerializedName("user_rating")
    @Embedded val user_rating: User_rating = User_rating(),

    @ColumnInfo(name = "thumb")
    @SerializedName("thumb") val thumb: String? = "",

    @ColumnInfo(name = "all_reviews_count")
    @SerializedName("all_reviews_count") val all_reviews_count: String = "",

    @ColumnInfo(name = "featured_image")
    @SerializedName("featured_image") val featured_image: String? = "",

    @ColumnInfo(name = "is_table_reservation_supported")
    @SerializedName("is_table_reservation_supported") val is_table_reservation_supported: String = "",

    @ColumnInfo(name = "phone_numbers")
    @SerializedName("phone_numbers") val phone_numbers: String = ""
) {
    data class R(
        @ColumnInfo(name = "res_id")
        @SerializedName("res_id") val res_id: Int = 0,
        @ColumnInfo(name = "is_grocery_store")
        @SerializedName("is_grocery_store") val is_grocery_store: Boolean = false,

        @SerializedName("has_menu_status")
        @Embedded val has_menu_status: Has_menu_status = Has_menu_status()
    )

    data class Location(
        @ColumnInfo(name = "address")
        @SerializedName("address") val address: String = "",
        @ColumnInfo(name = "locality")
        @SerializedName("locality") val locality: String = "",
        @ColumnInfo(name = "city")
        @SerializedName("city") val city: String = "",
        @ColumnInfo(name = "city_id")
        @SerializedName("city_id") val city_id : Int = 0,
        @ColumnInfo(name = "latitude")
        @SerializedName("latitude") val latitude: Double = 0.0,
        @ColumnInfo(name = "longitude")
        @SerializedName("longitude") val longitude: Double = 0.0,
        @ColumnInfo(name = "zipcode")
        @SerializedName("zipcode") val zipcode: String = "",
        @ColumnInfo(name = "country_id")
        @SerializedName("country_id") val country_id: String = ""
    )

    data class User_rating(
        @ColumnInfo(name = "aggregate_rating")
        @SerializedName("aggregate_rating") val aggregate_rating: Double = 0.0,
        @ColumnInfo(name = "rating_text")
        @SerializedName("rating_text") val rating_text: String = "",
        @ColumnInfo(name = "rating_color")
        @SerializedName("rating_color") val rating_color: String = "",

        @SerializedName("rating_obj")
        @Embedded val rating_obj: Rating_obj = Rating_obj(),

        @ColumnInfo(name = "votes")
        @SerializedName("votes") val votes: String = ""
    )

    data class Rating_obj(
        @SerializedName("bg_color")
        @Embedded val bg_color: Bg_color = Bg_color()
    )

    data class Bg_color(
        @ColumnInfo(name = "type")
        @SerializedName("type") val type: String = "",
        @ColumnInfo(name = "tint")
        @SerializedName("tint") val tint: String = ""
    )

    data class Has_menu_status(
        @ColumnInfo(name = "delivery")
        @SerializedName("delivery") val delivery: Int = 0,
        @ColumnInfo(name = "takeaway")
        @SerializedName("takeaway") val takeaway: Int = 0
    )

}