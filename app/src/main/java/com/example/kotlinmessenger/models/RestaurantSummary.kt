package com.example.kotlinmessenger.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kotlinmessenger.R
import com.google.gson.annotations.SerializedName

@Entity(tableName = "restaurants")
data class RestaurantSummary(
    @PrimaryKey
    @NonNull
    val primaryKey: String? = "",

    @ColumnInfo(name = "name")
    @SerializedName("name") val name: String = "",

    @SerializedName("R")
    @Embedded val r: R = R(),

    @ColumnInfo(name = "category_id")
    @SerializedName("category_id") var category_id: Int = 0,

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

    @SerializedName("user_rating")
    @Embedded val user_rating: User_rating = User_rating(),

    @SerializedName("thumb")
    @Embedded val thumb: String? = "",

    @ColumnInfo(name = "featured_image")
    @SerializedName("featured_image") val featured_image: String? = "",

    @ColumnInfo(name = "is_table_reservation_supported")
    @SerializedName("is_table_reservation_supported") val is_table_reservation_supported: String = ""

) {
    data class R(
        @ColumnInfo(name = "res_id")
        @SerializedName("res_id") val res_id: Int = 0,
        @ColumnInfo(name = "has_menu_status")
        @SerializedName("has_menu_status") val has_menu_status: Has_menu_status = Has_menu_status()
    )

     data class Location(
        @ColumnInfo(name = "address")
        @SerializedName("address") val address: String = "",
        @ColumnInfo(name = "locality")
        @SerializedName("locality") val locality: String = "",
        @ColumnInfo(name = "city")
        @SerializedName("city") val city: String = "",
        @ColumnInfo(name = "city_id")
        @SerializedName("city_id") val city_id : Int = 0
    )

    data class User_rating(
        @ColumnInfo(name = "aggregate_rating")
        @SerializedName("aggregate_rating") val aggregate_rating: Double = 0.0,
        @ColumnInfo(name = "votes")
        @SerializedName("votes") val votes: String = ""
    )

    data class Has_menu_status(
        @ColumnInfo(name = "delivery")
        @SerializedName("delivery") val delivery: Int = 0,
        @ColumnInfo(name = "takeaway")
        @SerializedName("takeaway") val takeaway: Int = 0
    )

}