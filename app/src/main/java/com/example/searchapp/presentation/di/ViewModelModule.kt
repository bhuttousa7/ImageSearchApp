package com.example.searchapp.presentation.di

import android.app.Application
import com.example.searchapp.data.util.SharedPreference
import com.example.searchapp.domain.usecase.ImageUseCase
import com.example.searchapp.presentation.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

	@Singleton
	@Provides
	fun providesHomeViewModel(app : Application, imageUseCase: ImageUseCase, sharedPreference: SharedPreference) : HomeViewModel {
		return HomeViewModel(app, imageUseCase, sharedPreference)
	}

}