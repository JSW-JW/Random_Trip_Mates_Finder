package com.example.kotlinmessenger.view.trip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.groupie.trip.CategoryItem
import com.example.kotlinmessenger.groupie.trip.CategoryListener
import com.example.kotlinmessenger.models.Restaurant
import com.example.kotlinmessenger.request.ServiceGenerator
import com.example.kotlinmessenger.util.Constants
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity(), CategoryListener {
    companion object {
        private const val TAG = "CategoryActivity"

        private lateinit var mAdapter: GroupAdapter<GroupieViewHolder>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        initRecyclerView()
    }


    private fun initRecyclerView() {
        mAdapter = GroupAdapter<GroupieViewHolder>()
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        for (i in Constants.DEFAULT_SEARCH_CATEGORIES.indices) {
            mAdapter.add(
                CategoryItem(
                    Constants.DEFAULT_SEARCH_CATEGORIES[i],
                    Constants.DEFAULT_SEARCH_CATEGORIES_ID[i],
                    Restaurant(name = Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i]),
                    this,
                    this@CategoryActivity
                )
            )
        }
        recycler_view.adapter = mAdapter
    }

    override fun onCategoryClick(categoryId: Int) {
        ServiceGenerator.retrofitService.searchWithCategory(categoryId)
            .enqueue(object : Callback<com.example.kotlinmessenger.models.Response> {
                override fun onFailure(
                    call: Call<com.example.kotlinmessenger.models.Response>,
                    t: Throwable
                ) {
                    CoroutineScope(Main).launch {
                        Toast.makeText(this@CategoryActivity, "Failed to retrieve data. please check network connection.", Toast.LENGTH_SHORT).show()
                    }
                    Log.d(TAG, "onFailure: ${t}")
                }


                override fun onResponse(
                    call: Call<com.example.kotlinmessenger.models.Response>,
                    response: Response<com.example.kotlinmessenger.models.Response>
                ) {
                    if (response.isSuccessful) {
                        /* for(restaurant in response.body()?.restaurants!!)
                         Log.d(TAG, "onResponse: $restaurant")*/
                        Log.d(TAG, "onResponse: ${response.body()?.restaurants}")
                    }
                }

            })
    }
}