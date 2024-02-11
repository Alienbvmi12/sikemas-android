package com.example.sikemasapp.data.storage

import android.content.Context
import android.content.SharedPreferences

class ForgotSessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "ForgotSession",
        Context.MODE_PRIVATE
    )

    fun saveEmail(email: String) {
        sharedPreferences.edit()
            .putString("email", email)
            .apply()
    }

    fun getOtp(): String? {
        return sharedPreferences.getString("otp", null)
    }

    fun saveOtp(otp: String) {
        sharedPreferences.edit()
            .putString("otp", otp)
            .apply()
    }

    fun getEmail(): String? {
        return sharedPreferences.getString("email", null)
    }


    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }

}