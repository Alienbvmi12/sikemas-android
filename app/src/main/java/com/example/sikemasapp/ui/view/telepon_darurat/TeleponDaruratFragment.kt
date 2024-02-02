package com.example.sikemasapp.ui.view.telepon_darurat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.teleponDarurat.TeleponDaruratViewModel
import com.example.sikemasapp.data.viewModel.teleponDarurat.TeleponDaruratViewModelFactory
import com.example.sikemasapp.databinding.FragmentLoginBinding
import com.example.sikemasapp.databinding.FragmentTeleponDaruratBinding
import com.example.sikemasapp.ui.adapters.TelpDaruratRecyclerViewAdapter

class TeleponDaruratFragment : Fragment() {
    private lateinit var binding: FragmentTeleponDaruratBinding
    private lateinit var viewModel: TeleponDaruratViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTeleponDaruratBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, TeleponDaruratViewModelFactory(requireContext()))
            .get(TeleponDaruratViewModel::class.java)

        binding.telpOptionRecyclerview.adapter = TelpDaruratRecyclerViewAdapter(viewModel.itemList, requireContext())
        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}