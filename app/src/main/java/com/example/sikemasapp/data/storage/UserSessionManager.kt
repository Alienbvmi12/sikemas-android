package com.example.sikemasapp.data.storage

import android.content.Context
import android.content.SharedPreferences

class UserSessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "UserSession",
        Context.MODE_PRIVATE
    )

    // Save token
    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    // Save other login info as needed
    fun saveLoginInfo(id: String, username: String, email: String) {
        sharedPreferences.edit()
            .putString("username", username)
            .putString("id", id)
            .putString("email", email)
            .apply()
    }

    // Retrieve token
    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    // Retrieve other login info
    fun getLoginInfo(): Map<String, *> {
        val username = sharedPreferences.getString("username", null)
        val email = sharedPreferences.getString("email", null)
        val id = sharedPreferences.getString("id", null)
        return mapOf("username" to username, "email" to email, "id" to id)
    }

    fun isLoginInfoExist(): Boolean {
        return sharedPreferences.getString("username", null) !== null
    }

    // Clear session (logout)
    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }
}
