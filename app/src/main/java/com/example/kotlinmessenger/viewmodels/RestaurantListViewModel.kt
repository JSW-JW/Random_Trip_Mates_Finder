package com.example.kotlinmessenger.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.kotlinmessenger.models.RestaurantSummary
import com.example.kotlinmessenger.repositories.RestaurantRepository
import com.example.kotlinmessenger.util.Resource

class RestaurantListViewModel(val restaurantRepository: RestaurantRepository) : ViewModel() {

    companion object {
        private const val TAG = "RestaurantListViewModel"
    }

    enum class ViewState {
        CATEGORIES, RESTAURANTS
    }

    private val viewState: MutableLiveData<ViewState> = MutableLiveData()
    private val results: MediatorLiveData<Resource<List<RestaurantSummary>?>?>? = MediatorLiveData()

    val restaurants: MediatorLiveData<Resource<List<RestaurantSummary>?>?>?
        get() = results

    init {
        viewState.value = ViewState.CATEGORIES
    }

    fun searchByCategoryId(category_id: Int) {
        executeSearch(category_id)

    }

    private fun executeSearch(category_id: Int) {
        val repositorySource = restaurantRepository.searchByCategoryId(category_id)

        if (repositorySource != null) {
            results?.addSource(repositorySource) { listResource ->
                if (repositorySource != null) {
                    if (listResource != null) {
                        results.value = listResource
                        if (listResource.status == Resource.Status.SUCCESS) {
                            // TODO: handle query exhausted
                            results.removeSource(repositorySource)
                        } else if (listResource.status == Resource.Status.ERROR) {
                            Log.d(TAG, "executeSearch: ERROR")
                            results.removeSource(repositorySource)
                        }
                    }
                } else {
                    results.removeSource(repositorySource as LiveData<Resource<List<RestaurantSummary>?>?>)
                }
            }
        }

    }


}