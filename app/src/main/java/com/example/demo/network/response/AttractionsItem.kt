package com.example.demo.network.response

import com.google.gson.annotations.SerializedName

data class AttractionsItem(
    val name: String,
    val introduction: String,
    @SerializedName("open_time")
    val openTime: String,
    val address: String,
    val url: String,
    var images: List<Image>
) {
    data class Image(
        val src: String,
        val ext: String
    )
}
