package com.example.searchapp.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.searchapp.data.util.Network.isNetworkAvailable
import com.example.searchapp.data.util.SharedPreference
import com.example.searchapp.domain.usecase.ImageUseCase
import com.example.searchapp.data.model.ImageItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response
import javax.inject.Inject
import kotlin.Exception

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app : Application,
    private val imageUseCase: ImageUseCase,
    private val sharedPreference: SharedPreference
) : AndroidViewModel(app){

    private val errorLiveData = MutableLiveData<String>()

    val images : MutableLiveData<Response<ImageItem>> = MutableLiveData()


    fun getImages(apiKey: String, query: String) = viewModelScope.launch(IO) {
        if (!isNetworkAvailable(app)) {
            val errorResponse = Response.error<ImageItem>(
                404,
                "Internet connection not available".toResponseBody(null)
            )
            images.postValue(errorResponse)
        } else {
            try {
                val result = null
                if (isNetworkAvailable(app)) {
                    val apiResult = imageUseCase.getImages(apiKey = apiKey, query = query)

                    images.postValue(apiResult)
                }
            } catch (e: Exception) {
                val errorResponse = Response.error<ImageItem>(
                    500,
                    "An error occurred: ${e.message}".toResponseBody(null)
                )
                images.postValue(errorResponse)
            }
        }
    }
}

