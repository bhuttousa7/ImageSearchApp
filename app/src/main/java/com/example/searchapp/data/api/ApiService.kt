package com.example.searchapp.data.api
import com.example.searchapp.data.model.ImageItem
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

	@GET("api/")
	suspend fun getImages(
		@Query("key") apiKey: String,
		@Query("q") query:  String,
		@Query("image_type") image_type: String = "photo"
	): Response<ImageItem>


}