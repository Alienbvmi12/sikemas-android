package com.example.sikemasapp.data.viewModel.kontak_pengaduan

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.data.model.login.LoginDataSource
import com.example.sikemasapp.data.model.login.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class KontakPengaduanViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KontakPengaduanViewModel::class.java)) {
            return KontakPengaduanViewModel(
                context = context
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}