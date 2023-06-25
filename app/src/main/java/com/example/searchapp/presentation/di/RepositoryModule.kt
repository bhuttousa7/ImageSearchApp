package com.example.searchapp.presentation.di

import com.example.searchapp.data.repository.ImageRepositoryImpl
import com.example.searchapp.data.repository.datasource.SearchRemoteDataSource
import com.example.searchapp.domain.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesSearchRepository(
        searchRemoteDataSource: SearchRemoteDataSource,
    ): ImageRepository {
        return ImageRepositoryImpl(
            searchRemoteDataSource = searchRemoteDataSource,
        )
    }
}