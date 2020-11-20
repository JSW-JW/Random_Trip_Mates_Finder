package com.example.kotlinmessenger.groupie.trip

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.models.Restaurants
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.restaurant_list_item_layout.view.*

class RestaurantListItem(private val restaurants: Restaurants, val context: Context) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.restaurant_name.text = restaurants.restaurant.name
        val address = restaurants.restaurant.location.city + restaurants.restaurant.location.address
        viewHolder.itemView.restaurant_address.text = address
        viewHolder.itemView.restaurant_timing.text = restaurants.restaurant.timings
        val currency = context.getString(R.string.currency, restaurants.restaurant.currency)
        viewHolder.itemView.restaurant_currency.text = currency

        val targetImageView = viewHolder.itemView.restaurant_image
        val image = restaurants.restaurant.featured_image ?: restaurants.restaurant.thumb

        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)

        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(image).into(targetImageView)


    }

    override fun getLayout(): Int {
        return R.layout.restaurant_list_item_layout
    }

}