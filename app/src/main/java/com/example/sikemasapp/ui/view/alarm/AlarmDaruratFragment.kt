package com.example.sikemasapp.ui.view.alarm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.data.viewModel.alarmDarurat.AlarmDaruratViewModel
import com.example.sikemasapp.data.viewModel.teleponDarurat.TeleponDaruratViewModel
import com.example.sikemasapp.data.viewModel.teleponDarurat.TeleponDaruratViewModelFactory
import com.example.sikemasapp.databinding.FragmentAlarmDaruratBinding
import com.example.sikemasapp.databinding.FragmentLoginBinding
import com.example.sikemasapp.ui.adapters.AlarmDaruratRecyclerViewAdapter
import com.example.sikemasapp.ui.adapters.TelpDaruratRecyclerViewAdapter

class AlarmDaruratFragment : Fragment() {
    private lateinit var binding: FragmentAlarmDaruratBinding
    private val viewModel: AlarmDaruratViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAlarmDaruratBinding.inflate(inflater, container, false)
        binding.alarmOptionRecyclerview.adapter = AlarmDaruratRecyclerViewAdapter(viewModel.itemList, requireContext())
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}