package com.example.searchapp

import com.example.searchapp.data.model.ImageItem
import com.example.searchapp.domain.repository.ImageRepository
import com.example.searchapp.domain.usecase.ImageUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ImageUseCaseTest {

    @Mock
    private lateinit var imageRepository: ImageRepository

    private lateinit var imageUseCase: ImageUseCase

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        imageUseCase = ImageUseCase(imageRepository)
    }

    @Test
    fun testGetImages_Success() = runBlocking {
        val mockResponse: Response<ImageItem> = Response.success(mockImageItem)
        `when`(imageRepository.getImages(anyString(), anyString())).thenReturn(mockResponse)
        val result = imageUseCase.getImages("apiKey", "query")

        assertEquals(mockResponse, result)
    }

    companion object {
        private val mockImageItem: ImageItem = ImageItem(
            hits = ArrayList(),
            total = 0,
            totalhits = 0
        )
    }
}