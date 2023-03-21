package com.example.demo.repository

import com.example.demo.network.AttractionsApi

class AttractionsRepository(private val attractionsApi: AttractionsApi) : BaseRepository() {

    suspend fun getAttractions(lang: String, page: Int) = safeApiCall {
        attractionsApi.getAttractions(lang, page)
    }

}