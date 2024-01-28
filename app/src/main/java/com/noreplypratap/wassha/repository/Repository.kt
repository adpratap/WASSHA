package com.noreplypratap.wassha.repository

import com.noreplypratap.wassha.model.ModelJoke
import retrofit2.Response

interface Repository {
    // For API
    suspend fun getRemoteData(): Response<ModelJoke>

    // For Local DB
    fun getLocalData(): ModelJoke?
    suspend fun saveData(modelJoke: ModelJoke)

    // Add More Interfaces ..
}