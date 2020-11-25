package com.example.kotlinmessenger.view.trip

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
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
import com.example.kotlinmessenger.viewmodels.QueryRestaurantViewModel
import com.example.kotlinmessenger.viewmodels.RestaurantListViewModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_restaurant_list.*

class RestaurantListActivity : BaseActivity(), CategoryListener, RestaurantListener {

    private val TAG = "CategoryActivity"
    private lateinit var mAdapter: GroupAdapter<GroupieViewHolder>
    private var mRestaurantListViewModel: RestaurantListViewModel? = null
    private var mQueryRestaurantViewModel: QueryRestaurantViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_list)

        provideViewModel()
        initRecyclerView()
        initSearchView()
        subscribeObservers()
    }

    private fun provideViewModel() {
        mRestaurantListViewModel =
            ViewModelProvider(this@RestaurantListActivity, RestaurantListViewModel.Factory(application)).get(RestaurantListViewModel::class.java)
        mQueryRestaurantViewModel =
            ViewModelProvider(this@RestaurantListActivity, QueryRestaurantViewModel.Factory(application)).get(QueryRestaurantViewModel::class.java)
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
                    Resource.Status.LOADING -> {
                        // Load animation from BaseActivity
                    }
                    Resource.Status.ERROR -> {
                        Toast.makeText(
                            this@RestaurantListActivity,
                            listResource.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    Resource.Status.SUCCESS -> {
                        if (listResource.data != null) {
                            mAdapter.clear()
                            for (restaurant in listResource.data) {
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
                }
            }
        })

        mQueryRestaurantViewModel?.restaurants?.observe(this, Observer { listResource ->
            if(listResource != null) {
                when(listResource.status) {
                    Resource.Status.LOADING -> {

                    }
                    Resource.Status.ERROR -> {

                    }
                    Resource.Status.SUCCESS -> {
                        if (listResource.data != null) {
                            mAdapter.clear()
                            for (restaurant in listResource.data) {
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
                }
            }
        })
    }

    private fun initSearchView(){
       search_view.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {
           override fun onQueryTextSubmit(query: String?): Boolean {
               mQueryRestaurantViewModel?.searchRestaurants(query)
               return false
           }

           override fun onQueryTextChange(newText: String?): Boolean {
               return false
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