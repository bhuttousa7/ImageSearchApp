package com.example.searchapp.presentation.di

import com.example.searchapp.data.api.ApiService
import com.example.searchapp.data.repository.datasource.SearchRemoteDataSource
import com.example.searchapp.data.repository.datasourceImpl.SearchRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

	@Provides
	@Singleton
	fun provideSearchRemoteDataSource(apiService: ApiService): SearchRemoteDataSource {
		return SearchRemoteDataSourceImpl(apiService = apiService)
	}
}