package com.example.sikemasapp.ui.view.alamat

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.data.viewModel.alamat.AlamatViewModel
import com.example.sikemasapp.data.viewModel.alamat.BalasanViewModelFactory
import com.example.sikemasapp.data.viewModel.alarmDarurat.AlarmDaruratViewModel
import com.example.sikemasapp.data.viewModel.balasan.BalasanViewModel
import com.example.sikemasapp.databinding.FragmentBalasanBinding
import com.example.sikemasapp.ui.adapters.AlamatAdapter
import com.example.sikemasapp.ui.adapters.BalasanAdapter
import com.example.sikemasapp.ui.component.BlackLoader

class BalasanFragment : Fragment() {
    private lateinit var binding: FragmentBalasanBinding
    private lateinit var viewModel: BalasanViewModel
    private lateinit var loader: BlackLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, BalasanViewModelFactory(requireContext()))
            .get(BalasanViewModel::class.java)
        viewModel.init()
        binding = FragmentBalasanBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModeru = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.alamatGallery.adapter = BalasanAdapter(requireContext(), viewModel,
        ){ message ->
            toast(message)
        }

        loader = BlackLoader(layoutInflater, binding.groupLayout)
        loader.addLoader(binding.root)

        viewModel.getBalasan{
            loader.hideLoader()
        }

        loader.showLoader()

        viewModel.alamatRes.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loginResult.error?.let {
                    toast(it)
                }
                loginResult.success?.let {

                }
            })
    }

    private fun toast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_LONG).show()
    }
}