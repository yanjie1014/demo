package com.example.demo.network.response

import com.google.gson.annotations.SerializedName

data class AttractionsResponse(
    val total: Int,
    @SerializedName("data")
    val attractions: List<AttractionsItem>
)
