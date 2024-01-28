package com.noreplypratap.wassha.repository

import com.noreplypratap.wassha.data.local.JokesDAO
import com.noreplypratap.wassha.data.remote.ServiceApi
import com.noreplypratap.wassha.model.ModelJoke
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi,
    private val jokesDAO: JokesDAO
) : Repository {

    // Remote Data ...
    override suspend fun getRemoteData(): Response<ModelJoke> {
        return serviceApi.getRemoteData()
    }

    // Local DATA
    override fun getLocalData(): ModelJoke? {
        return jokesDAO.getLocalJoke()
    }

    override suspend fun saveData(modelJoke: ModelJoke) {
        jokesDAO.insertJoke(modelJoke)
    }

}