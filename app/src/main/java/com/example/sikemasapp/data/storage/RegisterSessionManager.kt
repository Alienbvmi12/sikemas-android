package com.example.sikemasapp.data.storage

import android.content.Context
import android.content.SharedPreferences

class RegisterSessionManager(context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "RegisterSession",
        Context.MODE_PRIVATE
    )

    fun saveRegisterInfo(regId: String, username: String, email: String) {
        sharedPreferences.edit()
            .putString("reg_id", regId)
            .putString("username", username)
            .putString("email", email)
            .putInt("phase", EMAIL_VERIFICATION_PHASE)
            .apply()
    }

    fun getRegisterInfo(): Map<String, *> {
        val username = sharedPreferences.getString("username", null)
        val email = sharedPreferences.getString("email", null)
        val id = sharedPreferences.getString("reg_id", null)
        return mapOf("username" to username, "email" to email, "reg_id" to id)
    }

    fun getPhaseInfo(): Int {
        return sharedPreferences.getInt("phase", 0)
    }

    fun clearSession() {
        sharedPreferences.edit().clear().apply()
    }

    companion object {
        const val EMAIL_VERIFICATION_PHASE = 1
    }
}