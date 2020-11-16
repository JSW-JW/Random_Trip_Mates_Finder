package com.example.kotlinmessenger.groupie.trip

import android.net.Uri
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.models.Restaurant
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.category_list_item_layout.view.*

class CategoryItem(val text: String, val restaurant: Restaurant) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.category_title.text = text
        val targetImageView = viewHolder.itemView.category_image
        val path = Uri.parse("android.resource://com.example.kotlinmessenger/drawable/" + restaurant.category)
        Picasso.get().load(path).into(targetImageView)
    }

    override fun getLayout(): Int {
        return R.layout.category_list_item_layout
    }
}