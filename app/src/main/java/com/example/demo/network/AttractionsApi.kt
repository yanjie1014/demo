package com.example.demo.network

import com.example.demo.network.response.AttractionsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AttractionsApi {

    @GET("/{lang}/Attractions/All")
    suspend fun getAttractions(
        @Path("lang") lang: String,
        @Query("page") page: Int
    ): AttractionsResponse

}