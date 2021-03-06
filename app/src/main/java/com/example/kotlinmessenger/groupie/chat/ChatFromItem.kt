package com.example.kotlinmessenger.groupie.chat

import android.content.Context
import com.bumptech.glide.Glide
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.models.User
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.chat_from_row.view.*

class ChatFromItem(val text: String, val user : User, val context: Context) : Item<GroupieViewHolder>() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.itemView.chat_from_row.text = text
        val targetImageView = viewHolder.itemView.profile_chat_from_row
        val uri = user.profileImageUrl
        Glide.with(context).load(uri).into(targetImageView)
    }

    override fun getLayout(): Int {
        return R.layout.chat_from_row
    }
}