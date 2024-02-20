package com.example.sikemasapp.data.storage

import android.content.Context
import android.content.SharedPreferences

class ApiSessionManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "ApiSession",
        Context.MODE_PRIVATE
    )

    fun getApiUrl(): String?{
        return sharedPreferences.getString("api_url", null)
    }

    fun setApiUrl(url: String){
        sharedPreferences.edit()
            .putString("api_url", url)
            .apply()
    }
}