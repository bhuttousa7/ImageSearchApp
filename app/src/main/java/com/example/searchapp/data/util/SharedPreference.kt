package com.example.searchapp.data.util

import android.content.SharedPreferences
import com.example.searchapp.data.util.Constants
import javax.inject.Inject

class SharedPreference @Inject constructor(
    private val sharedPreferences : SharedPreferences
) {

    fun isFirstAppLaunch(): Boolean {
        return sharedPreferences.getBoolean("IS_FIRST_APP_LAUNCH", true)
    }

    fun saveFirstAppLaunch(value: Boolean) {
        sharedPreferences.edit().putBoolean("IS_FIRST_APP_LAUNCH", value).apply()
    }
    fun getLastSearchQuery(): String {
        return sharedPreferences.getString("lastSearchQuery", "fruits") ?: "fruits"
    }

    fun saveLastSearchQuery(query: String) {
        sharedPreferences.edit().putString("lastSearchQuery", query).apply()
    }


}