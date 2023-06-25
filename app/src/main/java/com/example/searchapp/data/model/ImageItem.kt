package com.example.searchapp.data.model

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

data class ImageItem(
    @SerializedName("hits")
    val hits: ArrayList<ImageResponse>,
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalhits")
    val totalhits: Int
)