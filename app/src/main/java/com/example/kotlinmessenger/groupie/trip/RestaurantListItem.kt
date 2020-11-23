package com.example.kotlinmessenger.groupie.trip

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.TextUtils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.models.RestaurantSummary
import com.example.kotlinmessenger.models.RestaurantWrapper
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.restaurant_list_item_layout.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class RestaurantListItem(private val restaurant: RestaurantSummary, val context: Context) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.restaurant_name.text = restaurant.name
        val locality = restaurant.location.city + restaurant.location.locality
        viewHolder.itemView.restaurant_locality.text = locality
        viewHolder.itemView.restaurant_timing.text = restaurant.timings
        val currency = context.getString(R.string.currency, restaurant.currency)
        viewHolder.itemView.restaurant_currency.text = currency

        val targetImageView = viewHolder.itemView.restaurant_image
        val image = restaurant.featured_image ?: restaurant.thumb

        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.no_photo)
            .centerCrop()

        val emptyCaseOptions: RequestOptions = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.no_photo)

        if(TextUtils.isEmpty(image)) {
            Glide.with(context)
                .setDefaultRequestOptions(emptyCaseOptions)
                .load(image).into(targetImageView)
        }
        else {
            Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(image).into(targetImageView)
        }

    }

    override fun getLayout(): Int {
        return R.layout.restaurant_list_item_layout
    }

}