package com.example.searchapp.presentation.di

import com.example.searchapp.data.model.ImageResponse
import javax.inject.Singleton

@Singleton
object ImageResponseHolder {
    var selectedImageResponse: ImageResponse? = null
}