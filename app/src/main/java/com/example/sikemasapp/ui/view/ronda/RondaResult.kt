package com.example.sikemasapp.ui.view.ronda

import com.example.sikemasapp.data.model.http.HttpResponseList

/**
 * Authentication result : success (user details) or error message.
 */
data class RondaResult(
    val success: HttpResponseList? = null,
    val error: String? = null
)