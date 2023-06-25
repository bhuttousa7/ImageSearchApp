package com.example.searchapp.presentation.di

import com.example.searchapp.data.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
	val BASE_URL = "https://pixabay.com/"
	@Provides
	@Singleton
	fun providesRetrofit(): Retrofit {
		val interceptor = HttpLoggingInterceptor().apply {
			this.level = HttpLoggingInterceptor.Level.BODY
		}

		val client = OkHttpClient.Builder().apply {
			this.addInterceptor(interceptor)
				.connectTimeout(30, TimeUnit.SECONDS)
				.readTimeout(20, TimeUnit.SECONDS)
				.writeTimeout(25, TimeUnit.SECONDS)
		}.build()

		return Retrofit.Builder()
			.addConverterFactory(GsonConverterFactory.create())
			.client(client)
			.baseUrl(BASE_URL)
			.build()
	}

	@Provides
	@Singleton
	fun providesSearchApiService(retrofit: Retrofit): ApiService {
		return retrofit.create(ApiService::class.java)
	}

	@Provides
	@Singleton
	fun providesGson(): Gson = GsonBuilder().create()
}