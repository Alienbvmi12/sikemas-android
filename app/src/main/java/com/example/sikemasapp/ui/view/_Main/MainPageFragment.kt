package com.example.sikemasapp.ui.view._Main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.main.MainActivityViewModel
import com.example.sikemasapp.databinding.ActivityMainBinding
import com.example.sikemasapp.databinding.FragmentAlarmDaruratBinding
import com.example.sikemasapp.databinding.FragmentMainBinding
import com.example.sikemasapp.ui.adapters.MainRecyclerViewAdapter
import com.example.sikemasapp.ui.adapters.MainRecyclerViewAdapter2
import com.example.sikemasapp.ui.view.profile.ProfileActivity
import com.google.android.material.appbar.MaterialToolbar

class MainPageFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.mainOptioRecyclerview
            .adapter = MainRecyclerViewAdapter2(viewModel.itemList2, requireContext()) { intege ->
                findNavController().navigate(intege)
            }
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}