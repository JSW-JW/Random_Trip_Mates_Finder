package com.example.kotlinmessenger.viewmodels

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinmessenger.models.Restaurant

class RestaurantListViewModel : ViewModel() {

    enum class ViewState {
        CATEGORIES, RESTAURANTS
    }

    private val viewState: MutableLiveData<ViewState> = MutableLiveData()
    private val results: MediatorLiveData<List<Restaurant>> = MediatorLiveData()

    private val restaurants: MediatorLiveData<List<Restaurant>>
        get() = results

    init {
        viewState.value = ViewState.CATEGORIES
    }

    fun searchRestaurantsApi(){

    }

    private fun executeSearch(){

    }


}