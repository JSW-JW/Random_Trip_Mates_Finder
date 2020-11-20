package com.example.kotlinmessenger.groupie.trip

import com.example.kotlinmessenger.R
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item

class RestaurantItem() : Item<GroupieViewHolder>() {

    override fun getLayout(): Int {
        return R.layout.restaurant_item_layout
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {

    }

}