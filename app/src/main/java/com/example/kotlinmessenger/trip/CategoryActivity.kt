package com.example.kotlinmessenger.trip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.groupie.trip.CategoryItem
import com.example.kotlinmessenger.groupie.trip.CategoryListener
import com.example.kotlinmessenger.request.ServiceGenerator
import com.example.kotlinmessenger.util.Constants
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_category.*
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

//        initRecyclerView()
        testApi()
    }


   /* private fun initRecyclerView(){
        mAdapter = GroupAdapter<GroupieViewHolder>()
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        for(i in Constants.DEFAULT_SEARCH_CATEGORIES.indices) {
            mAdapter.add(
                CategoryItem(
                    Constants.DEFAULT_SEARCH_CATEGORIES[i],
                    Restaurant(Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i],
                        "",
                        "",
                        Restaurant.R("",""),
                        Restaurant.Location("","", 0f, 0f, "", "")),
                    this
                )
            )
        }
        recycler_view.adapter = mAdapter
    }
*/

    private fun testApi(){
        ServiceGenerator.retrofitService.searchWithCategory(3).enqueue(object: Callback<com.example.kotlinmessenger.models.Response> {
            override fun onFailure(
                call: Call<com.example.kotlinmessenger.models.Response>,
                t: Throwable
            ) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

            override fun onResponse(
                call: Call<com.example.kotlinmessenger.models.Response>,
                response: Response<com.example.kotlinmessenger.models.Response>
            ) {
                if(response.isSuccessful) {
                    /* for(restaurant in response.body()?.restaurants!!)
                     Log.d(TAG, "onResponse: $restaurant")*/
                    Log.d(TAG, "onResponse: ${response.body()?.restaurants}")
                }
            }

        })

    }
    override fun onCategoryClick(categoryId: Int) {
        ServiceGenerator.retrofitService.searchWithCategory(3).enqueue(object: Callback<com.example.kotlinmessenger.models.Response> {
            override fun onFailure(
                call: Call<com.example.kotlinmessenger.models.Response>,
                t: Throwable
            ) {
                Log.d(TAG, "onFailure: ${t.message}")
            }

            override fun onResponse(
                call: Call<com.example.kotlinmessenger.models.Response>,
                response: Response<com.example.kotlinmessenger.models.Response>
            ) {
                if(response.isSuccessful) {
                   /* for(restaurant in response.body()?.restaurants!!)
                    Log.d(TAG, "onResponse: $restaurant")*/
                    Log.d(TAG, "onResponse: ${response.body()?.restaurants}")
                }
            }

        })
    }
}