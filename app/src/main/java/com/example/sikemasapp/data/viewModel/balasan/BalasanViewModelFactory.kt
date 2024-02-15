package com.example.sikemasapp.data.viewModel.alamat

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.data.viewModel.balasan.BalasanViewModel

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class BalasanViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BalasanViewModel::class.java)) {
            return BalasanViewModel(
                context = context
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}