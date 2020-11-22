package com.example.kotlinmessenger.repositories

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.kotlinmessenger.request.response.ApiResponse
import com.example.kotlinmessenger.util.Resource
import com.example.kotlinmessenger.util.Resource.Companion.loading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

// CacheObject: Type for the Resource data. (database cache)
// RequestObject: Type for the API response. (network request)
abstract class NetworkBoundResource<CacheObject, RequestObject> {

    companion object {
        private val TAG: String? = "NetworkBoundResource"
    }

    var results: MediatorLiveData<Resource<CacheObject?>?> = MediatorLiveData()

    init {
        init()
    }

    fun setValue(newValue: Resource<CacheObject?>?) {
        if (results.value !== newValue) {
            results.value = newValue
        }
    }

    private fun init() {

        // update LiveData for loading status
        results.value = loading(null)

        // observe LiveData source from local db
        val dbSource = loadFromDb()
        results.addSource(dbSource) { cacheObject ->
            results.removeSource(dbSource)
            if (shouldFetch(cacheObject)) {
                // get data from the network
                fetchFromNetwork(dbSource)
            } else {
                results.addSource(
                    dbSource
                ) { cacheObject -> setValue(Resource.success(cacheObject)) }
            }
        }
    }

    /**
     * 1) observe local db
     * 2) if <condition></condition> query the network
     * 3) stop observing the local db
     * 4) insert new data into local db
     * 5) begin observing local db again to see the refreshed data from network
     * @param dbSource
     */
    private fun fetchFromNetwork(dbSource: LiveData<CacheObject?>) {
        Log.d(TAG, "fetchFromNetwork: called.")

        // update LiveData for loading status
        results.addSource(
            dbSource
        ) { cacheObject -> setValue(loading(cacheObject)) }
        val apiResponse: LiveData<ApiResponse<RequestObject?>?> = createCall()
        results.addSource(
            apiResponse
        ) { requestObjectApiResponse ->
            results.removeSource(dbSource)
            results.removeSource(apiResponse)

            /*
               3 cases:
                1) ApiSuccessResponse
                2) ApiErrorResponse
                3) ApiEmptyResponse
             */
            when (requestObjectApiResponse) {
                is ApiResponse<*>.ApiSuccessResponse<*> -> {
                    Log.d(
                        TAG,
                        "onChanged: ApiSuccessResponse."
                    )
                    // save the response to the local db
                    CoroutineScope(IO).launch {
                        saveCallResult(processResponse(requestObjectApiResponse as ApiResponse<CacheObject>.ApiSuccessResponse<CacheObject>) as RequestObject)

                        withContext(Main) {
                            results.addSource(
                                loadFromDb()
                            ) { cacheObject -> setValue(Resource.success(cacheObject)) }
                        }
                    }
                }
                is ApiResponse<*>.ApiEmptyResponse<*> -> {
                    Log.d(
                        TAG,
                        "onChanged: ApiEmptyResponse"
                    )
                    CoroutineScope(Main).launch {
                        results.addSource(
                            loadFromDb()
                        ) { cacheObject -> setValue(Resource.success(cacheObject)) }
                    }
                }
                is ApiResponse<*>.ApiErrorResponse<*> -> {
                    Log.d(
                        TAG,
                        "onChanged: ApiErrorResponse."
                    )
                    results.addSource(
                        loadFromDb()
                    ) { cacheObject ->
                        setValue(
                            Resource.error(
                                (requestObjectApiResponse as ApiResponse<*>.ApiErrorResponse<*>).errorMessage,
                                cacheObject
                            )
                        )
                    }
                }
            }
        }
    }

    private fun processResponse(response: ApiResponse<CacheObject>.ApiSuccessResponse<CacheObject>): CacheObject? {
        return response.body
    }

    // Called to save the result of the API response into the database.
    @WorkerThread
    protected abstract fun saveCallResult(item: RequestObject)

    // Called with the data in the database to decide whether to fetch
    // potentially updated data from the network.
    @MainThread
    protected abstract fun shouldFetch(data: CacheObject?): Boolean

    // Called to get the cached data from the database.
    @MainThread
    protected abstract fun loadFromDb(): LiveData<CacheObject?>

    // Called to create the API call.
    @MainThread
    protected abstract fun createCall(): LiveData<ApiResponse<RequestObject?>?>

    // Returns a LiveData object that represents the resource that's implemented
    // in the base class.
    val asLiveData: LiveData<Resource<CacheObject?>?>
        get() = results

}
