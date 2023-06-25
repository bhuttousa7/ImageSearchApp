package com.example.searchapp.domain.usecase

import com.example.searchapp.domain.repository.ImageRepository
import com.example.searchapp.data.model.ImageItem
import retrofit2.Response
import javax.inject.Inject

class ImageUseCase @Inject constructor(
    private val imageRepository: ImageRepository
) {
    suspend fun getImages(apiKey: String, query: String): Response<ImageItem> {
        return imageRepository.getImages(apiKey = apiKey, query = query)
    }
}