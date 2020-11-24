package com.example.kotlinmessenger.view.trip

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.kotlinmessenger.R
import com.example.kotlinmessenger.models.RestaurantDetail
import com.example.kotlinmessenger.util.Resource
import com.example.kotlinmessenger.view.BaseActivity
import com.example.kotlinmessenger.viewmodels.RestaurantViewModel
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantActivity : BaseActivity() {

    companion object {
        private const val TAG = "RestaurantActivity"
    }

    private var mRestaurantViewModel: RestaurantViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)

        provideViewModel()
        subscribeObservers()
        checkIntentExtra()
    }

    private fun checkIntentExtra() {
        if (intent.hasExtra("resId")) {
            val resId = intent.getIntExtra("resId", 0)
            searchRestaurantById(resId)
        } else {
            Toast.makeText(this, "Cannot check the proper restaurant id.", Toast.LENGTH_LONG).show()
        }
    }

    private fun searchRestaurantById(resId: Int) {
        mRestaurantViewModel?.searchByRestaurantId(resId)
    }

    private fun provideViewModel() {
        mRestaurantViewModel =
            ViewModelProvider(this, RestaurantViewModel.Factory(application)).get(
                RestaurantViewModel::class.java
            )
    }

    private fun subscribeObservers() {
        mRestaurantViewModel?.restaurantDetail?.observe(this, Observer { detailResource ->
            if (detailResource?.status != null) {
                when (detailResource.status) {
                    Resource.Status.SUCCESS -> {
                        detailResource.data?.let { restaurantDetail ->
                            Log.d(TAG, "subscribeObservers: $restaurantDetail")
                            setRestaurantProperties(restaurantDetail)
                        }
                    }
                    Resource.Status.ERROR -> {

                    }
                    Resource.Status.LOADING -> {

                    }
                }
            }
        })
    }

    private fun setRestaurantProperties(restaurantDetail: RestaurantDetail) {
        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
        val targetImageView = restaurant_image

        Glide.with(this)
            .setDefaultRequestOptions(options)
            .load(restaurantDetail.featured_image).into(targetImageView) // set image
        restaurant_phone_number.text = restaurantDetail.phone_numbers // set phone number
        restaurant_timing.text = restaurantDetail.timings // set operating time
        restaurant_highlights.text =
            restaurantDetail.highlights.toString() // set highlighting keyword of restaurant
        setOptions(restaurantDetail) // set options data
        setUserRating(restaurantDetail) // set userRating data
    }

    private fun setOptions(restaurantDetail: RestaurantDetail) {
        restaurant_r_isGroceryStore.text =
            if (restaurantDetail.r.is_grocery_store) "true" else "false"
        restaurant_r_delivery.text =
            if (restaurantDetail.r.has_menu_status.delivery != -1) "true" else "false"
        restaurant_r_takeaway.text =
            if (restaurantDetail.r.has_menu_status.takeaway != -1) "true" else "false"

    }

    private fun setUserRating(restaurantDetail: RestaurantDetail) {
        restaurant_userRating_aggregate_rating.text =
            restaurantDetail.user_rating.aggregate_rating.toString()
        restaurant_userRating_rating_text.text = restaurantDetail.user_rating.rating_text

    }
}