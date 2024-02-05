package com.noreplypratap.wassha.repository

import com.noreplypratap.wassha.model.ModelJoke

interface Repository {
    // For API
    suspend fun getRemoteData(): ModelJoke?

    // For Local DB
    fun getLocalData(): ModelJoke?

    // Add More Interfaces ..
}