package com.example.searchapp

import com.example.searchapp.data.model.ImageItem
import com.example.searchapp.data.repository.ImageRepositoryImpl
import com.example.searchapp.data.repository.datasource.SearchRemoteDataSource
import com.example.searchapp.domain.repository.ImageRepository
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
class ImageRepositoryTest {

    @Mock
    private lateinit var searchRemoteDataSource: SearchRemoteDataSource

    private lateinit var imageRepository: ImageRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        imageRepository = ImageRepositoryImpl(searchRemoteDataSource)
    }

    @Test
    fun testGetImages_Success() = runBlocking {
        val mockResponse: Response<ImageItem> = Response.success(mockImageItem)
        `when`(searchRemoteDataSource.getImages(anyString(), anyString())).thenReturn(mockResponse)
        val result = imageRepository.getImages("apiKey", "query")
        assertEquals(result, mockResponse)
    }


    companion object {
        private val mockImageItem: ImageItem = ImageItem(
            hits = ArrayList(),
            total = 0,
            totalhits = 0
        )
    }
}