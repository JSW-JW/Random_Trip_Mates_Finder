package com.example.kotlinmessenger.util

import androidx.lifecycle.LiveData
import com.example.kotlinmessenger.request.response.ApiResponse
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class LiveDataCallAdapterFactory : CallAdapter.Factory() {
    /**
     * This method performs a number of checks and then returns the Response type for the Retrofit requests
     * (@bodyType is the ResponseType. It can be RecipeResponse or RecipeSearchResponse)
     *
     * CHECK #1) returnType returns LIVEDATA
     * CHECK #2) Type LiveData<T> is of ApiResponse.class
     * CHECK #3) Make sure ApiResponse is parameeterized. AKA: ApiResponse<T> exists.
     *
     *
     * @param returnType
     * @param annotations
     * @param retrofit
     * @return
    </T></T> */
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {

        // Check #1
        // Make sure the CallAdapter is returning a type of LiveData
        if (getRawType(returnType) != LiveData::class.java) {
            return null
        }

        // Check #2
        // Type that LiveData is wrapping
        val observableType = getParameterUpperBound(
            0,
            returnType as ParameterizedType
        )
        // Check if it's of Type ApiResponse
        val rawObservableType: Type =
            getRawType(observableType)
        require(!(rawObservableType !== ApiResponse::class.java)) { "type must be a defined resource" }

        // Check #3
        // Check if ApiResponse is parameterized. AKA: Does ApiResponse<T> exist? (must wrap around T)
        // FYI: T is either RecipeResponse or RecipeSearchResponse in this app. But T can be anything theoretically.
        require(observableType is ParameterizedType) { "resource must be parameterized" }

        // get the Response type. (RecipeSearchResponse or RecipeResponse)
        val bodyType = getParameterUpperBound(
            0,
            observableType
        )
        return LiveDataCallAdapter<Type>(bodyType)
    }
}