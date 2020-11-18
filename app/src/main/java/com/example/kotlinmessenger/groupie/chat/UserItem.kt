package com.example.kotlinmessenger.groupie.chat

import android.content.Context
import com.bumptech.glide.Glide
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.models.User
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.user_row_new_message.view.*

class UserItem(val user : User, val context: Context) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.username_textview_new_message.text = user.username

        Glide.with(context).load(user.profileImageUrl).into(viewHolder.itemView.imageview_new_message_row)

        // will be called in our list for each user object later on...
    }
    override fun getLayout(): Int {
        return R.layout.user_row_new_message
    }
}