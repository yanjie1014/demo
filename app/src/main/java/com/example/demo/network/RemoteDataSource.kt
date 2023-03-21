package com.example.demo.network

import com.example.demo.app.App
import com.example.demo.utility.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RemoteDataSource {

    @Inject
    lateinit var ok: OkHttpClient

    fun <T> buildApi(api: Class<T>): T {
        (App.instance as App).component.inject(this)

        return Retrofit.Builder()
            .baseUrl(Constants.Network.BASE_URL)
            .client(
                ok
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}