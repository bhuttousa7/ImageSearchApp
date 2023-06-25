package com.example.searchapp.data.repository.datasource

import com.example.searchapp.data.model.ImageItem
import retrofit2.Response

interface SearchRemoteDataSource {
	suspend fun getImages(apiKey: String, query: String) : Response<ImageItem>
}