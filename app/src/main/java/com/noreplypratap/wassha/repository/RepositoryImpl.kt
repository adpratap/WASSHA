package com.noreplypratap.wassha.repository

import com.noreplypratap.wassha.data.local.JokesDAO
import com.noreplypratap.wassha.data.remote.ServiceApi
import com.noreplypratap.wassha.model.ModelJoke
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi,
    private val jokesDAO: JokesDAO
) : Repository {

    // Remote Data ...
    override suspend fun getRemoteData(): ModelJoke? {
        try {
            val res = serviceApi.getRemoteData()
            if (res.isSuccessful) {
                res.body()?.let { jokesData ->

                    //Saving to DB ...
                    jokesDAO.insertJoke(jokesData)
                    return jokesData
                }
            }
        } catch (e: Exception) {
           // Handle Exception...
        }
        return null
    }

    // Local DATA
    override fun getLocalData(): ModelJoke? {
        return jokesDAO.getLocalJoke()
    }

}