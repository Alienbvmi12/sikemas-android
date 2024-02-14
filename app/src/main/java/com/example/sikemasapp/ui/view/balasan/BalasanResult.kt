package com.example.sikemasapp.ui.view.balasan

import com.example.sikemasapp.data.model.http.HttpResponseAlamat
import com.example.sikemasapp.data.model.http.HttpResponseBalasan
import com.example.sikemasapp.data.model.http.HttpResponseList

/**
 * Authentication result : success (user details) or error message.
 */
data class BalasanResult(
    val success: HttpResponseBalasan? = null,
    val error: String? = null
)