package com.example.sikemasapp.ui.view.telepon_darurat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.teleponDarurat.TeleponDaruratViewModel
import com.example.sikemasapp.data.viewModel.teleponDarurat.TeleponDaruratViewModelFactory
import com.example.sikemasapp.databinding.FragmentTeleponDaruratBinding
import com.example.sikemasapp.ui.adapters.TelpDaruratRecyclerViewAdapter

class TeleponDaruratActivity : AppCompatActivity() {
    private lateinit var binding: FragmentTeleponDaruratBinding
    private lateinit var viewModel: TeleponDaruratViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentTeleponDaruratBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this, TeleponDaruratViewModelFactory(this))
            .get(TeleponDaruratViewModel::class.java)

        binding.telpOptionRecyclerview.adapter = TelpDaruratRecyclerViewAdapter(viewModel.itemList, this)
        setContentView(binding.root)
    }
}