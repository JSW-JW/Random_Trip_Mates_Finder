package com.example.kotlinmessenger.groupie.trip

import android.content.Context
import android.net.Uri
import com.bumptech.glide.Glide
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.models.RestaurantSummary
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.category_list_item_layout.view.*

class CategoryListItem(private val text: String, private val category_id: Int, private val restaurant: RestaurantSummary, private val categoryListener: CategoryListener, val context: Context) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.category_title.text = text
        val targetImageView = viewHolder.itemView.category_image
        val path = Uri.parse("android.resource://com.example.kotlinmessenger/drawable/" + restaurant.name)
        Glide.with(context).load(path).into(targetImageView)

        viewHolder.itemView.setOnClickListener {
            categoryListener.onCategoryClick(category_id)

        }
    }

    override fun getLayout(): Int {
        return R.layout.category_list_item_layout
    }

}