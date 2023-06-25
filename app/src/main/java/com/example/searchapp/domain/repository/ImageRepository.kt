package com.example.searchapp.domain.repository

import com.example.searchapp.data.model.ImageItem
import retrofit2.Response

interface ImageRepository {

	suspend fun getImages(apiKey: String, query: String) : Response<ImageItem>

}