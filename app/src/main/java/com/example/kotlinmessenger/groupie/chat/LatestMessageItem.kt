package com.example.kotlinmessenger.groupie.chat

import android.content.Context
import com.bumptech.glide.Glide
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.models.ChatMessage
import com.example.kotlinmessenger.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.xwray.groupie.GroupieViewHolder
import com.xwray.groupie.Item
import kotlinx.android.synthetic.main.latest_message_row.view.*

class LatestMessageItem(val chatMessage: ChatMessage, val context: Context): Item<GroupieViewHolder>(

) {
    var chatPartner : User? = null

    override fun getLayout(): Int {
        return R.layout.latest_message_row
    }

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val chatPartnerId = if(chatMessage.fromId == FirebaseAuth.getInstance().uid)
            chatMessage.toId else chatMessage.fromId

        val ref = FirebaseDatabase.getInstance().getReference("/user/$chatPartnerId")
        ref.addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
            }
            override fun onDataChange(snapshot: DataSnapshot) {
                chatPartner = snapshot.getValue(User::class.java)
                viewHolder.itemView.username_latest.text = chatPartner?.username

                val targetImageView = viewHolder.itemView.image_view_Latest
                Glide.with(context).load(chatPartner?.profileImageUrl).into(targetImageView)
            }
        })
        viewHolder.itemView.message_latest.text = chatMessage.text

    }
}