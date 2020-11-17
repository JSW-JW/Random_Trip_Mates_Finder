package com.example.kotlinmessenger.groupie.trip

import android.net.Uri
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.models.Restaurant
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.category_list_item_layout.view.*

class CategoryItem(private val text: String, private val restaurant: Restaurant, val categoryListener: CategoryListener) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.category_title.text = text
        val targetImageView = viewHolder.itemView.category_image
        val path = Uri.parse("android.resource://com.example.kotlinmessenger/drawable/" + restaurant.name)
        Picasso.get().load(path).into(targetImageView)

        viewHolder.itemView.setOnClickListener {
            categoryListener.onCategoryClick(position)
        }
    }

    override fun getLayout(): Int {
        return R.layout.category_list_item_layout
    }


}