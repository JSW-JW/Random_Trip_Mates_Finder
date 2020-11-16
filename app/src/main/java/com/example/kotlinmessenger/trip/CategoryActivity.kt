package com.example.kotlinmessenger.trip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.groupie.trip.CategoryItem
import com.example.kotlinmessenger.models.Restaurant
import com.example.kotlinmessenger.util.Constants
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {
    companion object {
        private lateinit var mAdapter: GroupAdapter<GroupieViewHolder>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        initRecyclerView()
    }


    private fun initRecyclerView(){
        mAdapter = GroupAdapter<GroupieViewHolder>()
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        for(i in Constants.DEFAULT_SEARCH_CATEGORIES.indices) {
            mAdapter.add(
                CategoryItem(
                    Constants.DEFAULT_SEARCH_CATEGORIES[i],
                    Restaurant(category = Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i])
                )
            )
        }
        recycler_view.adapter = mAdapter
    }
}