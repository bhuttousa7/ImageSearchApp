package com.example.searchapp.presentation.di

import com.example.searchapp.domain.repository.ImageRepository
import com.example.searchapp.domain.usecase.ImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

	@Provides
	@Singleton
	fun providesImageUseCase(imageRepository: ImageRepository): ImageUseCase {
		return ImageUseCase(imageRepository = imageRepository)
	}
}