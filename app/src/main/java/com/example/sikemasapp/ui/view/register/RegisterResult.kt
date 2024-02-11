package com.example.sikemasapp.ui.view.register

/**
 * Authentication result : success (user details) or error message.
 */
data class RegisterResult(
    val success: LoggedInUserView? = null,
    val error: String? = null
)