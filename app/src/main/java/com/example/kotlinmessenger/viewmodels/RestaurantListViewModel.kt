package com.example.kotlinmessenger.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.kotlinmessenger.models.RestaurantSummary
import com.example.kotlinmessenger.repositories.RestaurantRepository
import com.example.kotlinmessenger.util.Resource

class RestaurantListViewModel(application: Application) : AndroidViewModel(application) {

    class Factory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RestaurantListViewModel(application) as T
        }
    }

    companion object {
        private const val TAG = "RestaurantListViewModel"
    }

    enum class ViewState {
        CATEGORIES, RESTAURANTS
    }

    private var mRestaurantRepository: RestaurantRepository = RestaurantRepository.instance(application)
    private val viewState: MutableLiveData<ViewState> = MutableLiveData()
    private val results: MediatorLiveData<Resource<List<RestaurantSummary>?>?> = MediatorLiveData()

    val restaurants: LiveData<Resource<List<RestaurantSummary>?>?>
        get() = results

    init {
        viewState.value = ViewState.CATEGORIES
    }

    fun searchByCategoryId(category_id: Int) {
        executeSearch(category_id)

    }

    private fun executeSearch(category_id: Int) {
        val repositorySource: LiveData<Resource<List<RestaurantSummary>?>?> =
            mRestaurantRepository.searchByCategoryId(category_id)

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