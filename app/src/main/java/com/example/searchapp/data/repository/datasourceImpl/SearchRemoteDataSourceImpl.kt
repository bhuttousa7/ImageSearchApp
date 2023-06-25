package com.example.searchapp.data.repository.datasourceImpl

import com.example.searchapp.data.api.ApiService
import com.example.searchapp.data.repository.datasource.SearchRemoteDataSource
import com.example.searchapp.data.model.ImageItem
import retrofit2.Response
import javax.inject.Inject

class SearchRemoteDataSourceImpl @Inject constructor(
	private val apiService: ApiService
) : SearchRemoteDataSource {

	override suspend fun getImages(apiKey: String, query: String): Response<ImageItem> {
		return apiService.getImages(apiKey = apiKey, query = query)
	}

}