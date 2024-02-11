package com.example.sikemasapp.ui.view._Main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sikemasapp.data.viewModel.main.MainActivityViewModel
import com.example.sikemasapp.data.viewModel.main.MainViewModelFactory
import com.example.sikemasapp.databinding.FragmentMainBinding
import com.example.sikemasapp.ui.adapters.MainRecyclerViewAdapter2

class MainPageFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, MainViewModelFactory(requireContext()))
            .get(MainActivityViewModel::class.java)
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