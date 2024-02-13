package com.example.sikemasapp.ui.view.kontak_pengaduan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.MainActivity2
import com.example.sikemasapp.data.viewModel.kontak_pengaduan.KontakPengaduanViewModel
import com.example.sikemasapp.data.viewModel.kontak_pengaduan.KontakPengaduanViewModelFactory
import com.example.sikemasapp.data.viewModel.ronda.RondaViewModel
import com.example.sikemasapp.data.viewModel.ronda.RondaViewModelFactory
import com.example.sikemasapp.databinding.FragmentKontakPengaduanBinding
import com.example.sikemasapp.databinding.FragmentLoginBinding
import com.example.sikemasapp.ui.component.BlackLoader

class KontakPengaduanFragment : Fragment() {
    private lateinit var binding: FragmentKontakPengaduanBinding
    private lateinit var viewModel: KontakPengaduanViewModel
    private lateinit var loader: BlackLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, KontakPengaduanViewModelFactory(requireContext()))
            .get(KontakPengaduanViewModel::class.java)
        binding = FragmentKontakPengaduanBinding.inflate(inflater, container, false)
        loader = BlackLoader(inflater, container)
        loader.addLoader(binding.root)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitKeluhan.setOnClickListener{
            loader.showLoader()
            viewModel.postAspirasi(
                binding.inputPerihal.text.toString(),
                binding.inputPesan.text.toString()){

            }
        }

        viewModel.aspRes.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loader.hideLoader()
                loginResult.error?.let {
                    toast(it)
                }
                loginResult.success?.let {
                    toast(it.displayName)
                }
            })
    }

    private fun toast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_LONG).show()
    }
}