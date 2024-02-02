package com.example.sikemasapp.data.viewModel.teleponDarurat

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class TeleponDaruratViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeleponDaruratViewModel::class.java)) {
            return TeleponDaruratViewModel(
                context = context
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}