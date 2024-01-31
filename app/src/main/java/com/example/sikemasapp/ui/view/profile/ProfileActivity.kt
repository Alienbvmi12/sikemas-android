package com.example.sikemasapp.ui.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.alarmDarurat.TeleponDaruratViewModel
import com.example.sikemasapp.data.viewModel.alarmDarurat.TeleponDaruratViewModelFactory
import com.example.sikemasapp.databinding.FragmentTeleponDaruratBinding
import com.example.sikemasapp.ui.adapters.TelpDaruratRecyclerViewAdapter

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: FragmentTeleponDaruratBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTeleponDaruratBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}