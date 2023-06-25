package com.example.searchapp.data.repository

import com.example.searchapp.data.repository.datasource.SearchRemoteDataSource
import com.example.searchapp.domain.repository.ImageRepository
import com.example.searchapp.data.model.ImageItem
import com.example.searchapp.data.model.ImageResponse
import com.example.searchapp.data.repository.datasource.SearchLocalDataSource
import retrofit2.Response
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
	private val searchRemoteDataSource: SearchRemoteDataSource,
) : ImageRepository {

	override suspend fun getImages(apiKey: String, query: String): Response<ImageItem> {
		return searchRemoteDataSource.getImages(apiKey = apiKey, query = query)
	}

}




