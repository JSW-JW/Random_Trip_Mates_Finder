package com.example.kotlinmessenger.view.trip

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.groupie.trip.*
import com.example.kotlinmessenger.models.RestaurantSummary
import com.example.kotlinmessenger.util.Constants
import com.example.kotlinmessenger.util.Resource
import com.example.kotlinmessenger.view.BaseActivity
import com.example.kotlinmessenger.viewmodels.RestaurantListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_category.*

class RestaurantListActivity : BaseActivity(), CategoryListener, RestaurantListener {

    private val TAG = "CategoryActivity"
    private lateinit var mAdapter: GroupAdapter<GroupieViewHolder>
    private var mRestaurantListViewModel: RestaurantListViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        provideViewModel()
        initRecyclerView()
        subscribeObservers()
    }

    private fun provideViewModel() {
        mRestaurantListViewModel =
            ViewModelProvider(this@RestaurantListActivity, RestaurantListViewModel.Factory(application)).get(RestaurantListViewModel::class.java)
    }

    private fun initRecyclerView() {
        mAdapter = GroupAdapter<GroupieViewHolder>()
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        for (i in Constants.DEFAULT_SEARCH_CATEGORIES.indices) {
            mAdapter.add(
                CategoryListItem(
                    Constants.DEFAULT_SEARCH_CATEGORIES[i],
                    Constants.DEFAULT_SEARCH_CATEGORIES_ID[i],
                    RestaurantSummary(name = Constants.DEFAULT_SEARCH_CATEGORY_IMAGES[i], primaryKey = 0),
                    this,
                    this@RestaurantListActivity
                )
            )
        }
        recycler_view.adapter = mAdapter
    }

    private fun subscribeObservers() {
        mRestaurantListViewModel?.restaurants?.observe(this, Observer { listResource ->
            if (listResource != null) {
                when (listResource.status) {
                    Resource.Status.SUCCESS -> {
                        if (listResource.data != null) {
                            mAdapter.clear()
                            for (restaurant in listResource.data) {
                                Log.d(TAG, "onResponse: $restaurant")
                                recycler_view.addItemDecoration(RestaurantItemDecoration(2))
                                mAdapter.add(
                                    RestaurantListItem(
                                        restaurant,
                                        this@RestaurantListActivity,
                                        this
                                    )
                                )
                                mAdapter.notifyDataSetChanged()
                            }
                        }
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(
                            this@RestaurantListActivity,
                            "Failed to retrieve data. please check network connection.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Resource.Status.LOADING -> {
                        // Load animation from BaseActivity
                    }
                }
            }
        })
    }

    override fun onCategoryClick(categoryId: Int) {
        mRestaurantListViewModel?.searchByCategoryId(categoryId)
    }

    override fun onItemClick(resId: Int) {
        val intent = Intent(this, RestaurantActivity::class.java)
        intent.putExtra("resId", resId)
        startActivity(intent)
    }
}