package com.example.sikemasapp.data.viewModel.ronda

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.data.model.login.LoginDataSource
import com.example.sikemasapp.data.model.login.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class RondaViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RondaViewModel::class.java)) {
            return RondaViewModel(
                context = context
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}