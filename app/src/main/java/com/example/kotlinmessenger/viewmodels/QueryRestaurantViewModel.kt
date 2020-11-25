package com.example.kotlinmessenger.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.example.kotlinmessenger.models.RestaurantSummary
import com.example.kotlinmessenger.repositories.RestaurantRepository
import com.example.kotlinmessenger.util.Resource

class QueryRestaurantViewModel(application: Application) : AndroidViewModel(application) {

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return QueryRestaurantViewModel(application) as T
        }
    }

    companion object {
        private const val TAG = "CityRestaurantViewModel"
    }

    private var mRestaurantRepository: RestaurantRepository = RestaurantRepository.instance(application)
    private val results: MediatorLiveData<Resource<List<RestaurantSummary>?>?> = MediatorLiveData()

    val restaurants: MediatorLiveData<Resource<List<RestaurantSummary>?>?>
        get() = results

    fun searchRestaurants(query: String?) {
        executeSearch(query)

    }

    private fun executeSearch(query: String?) {
        val repositorySource: LiveData<Resource<List<RestaurantSummary>?>?> =
            mRestaurantRepository.searchRestaurant(query)

        results.addSource(repositorySource) { listResource ->
            if (listResource != null) {
                results.value = listResource
                if (listResource.status == Resource.Status.SUCCESS) {
                    // TODO: handle query exhausted
                    results.removeSource(repositorySource)
                } else if (listResource.status == Resource.Status.ERROR) {
                    results.removeSource(repositorySource)
                }
            } else {
                results.removeSource(repositorySource)
            }

        }


    }


}