package com.noreplypratap.wassha.data.remote

import com.noreplypratap.wassha.model.ModelJoke
import com.noreplypratap.wassha.utils.ConstantsAPI
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    // End Points ...
    @GET("Programming")
    suspend fun getRemoteData(
        @Query("type")
        type : String = ConstantsAPI.TYPE
    ) : Response<ModelJoke>


    // More End Points ...
}