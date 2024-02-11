package com.example.sikemasapp.ui.view.profile

import com.example.sikemasapp.data.model.http.HttpResponse

/**
 * Authentication result : success (user details) or error message.
 */
data class ProfileResult(
    val success: HttpResponse? = null,
    val error: String? = null
)