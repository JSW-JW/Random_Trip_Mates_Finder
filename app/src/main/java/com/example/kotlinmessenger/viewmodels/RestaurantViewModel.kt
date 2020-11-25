package com.example.kotlinmessenger.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.kotlinmessenger.models.RestaurantDetail
import com.example.kotlinmessenger.models.RestaurantSummary
import com.example.kotlinmessenger.repositories.RestaurantRepository
import com.example.kotlinmessenger.util.Resource

class RestaurantViewModel(application: Application): AndroidViewModel(application) {

    companion object {
        private const val TAG = "RestaurantViewModel"
    }

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RestaurantViewModel(application) as T
        }
    }

    private val mRestaurantRepository: RestaurantRepository = RestaurantRepository.instance(application)
    private val results: MediatorLiveData<Resource<RestaurantDetail?>?> = MediatorLiveData()

    val restaurantDetail: MediatorLiveData<Resource<RestaurantDetail?>?>
        get() = results

    fun searchByRestaurantId(resId: Int) {
        executeSearch(resId)
    }

    private fun executeSearch(resId: Int) {
        val repositorySource = mRestaurantRepository.searchByRestaurantId(resId)

        results.addSource(repositorySource) { detailResource ->
            if(detailResource != null) {
                Log.d(TAG, "executeSearch: $detailResource")
                results.value = detailResource

                results.removeSource(repositorySource)
            } else {
                results.removeSource(repositorySource)
            }
        }
    }
}