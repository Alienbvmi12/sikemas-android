package com.example.sikemasapp.ui.view.alamat

import com.example.sikemasapp.data.model.http.HttpResponseAlamat
import com.example.sikemasapp.data.model.http.HttpResponseList

/**
 * Authentication result : success (user details) or error message.
 */
data class AlamatResult(
    val success: HttpResponseAlamat? = null,
    val error: String? = null
)