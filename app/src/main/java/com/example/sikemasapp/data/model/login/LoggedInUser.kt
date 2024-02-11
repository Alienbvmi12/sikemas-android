package com.example.sikemasapp.data.model.login

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val id: String,
    val username: String,
    val email: String,
    val token: String
)