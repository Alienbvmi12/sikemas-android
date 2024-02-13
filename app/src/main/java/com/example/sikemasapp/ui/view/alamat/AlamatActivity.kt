package com.example.sikemasapp.ui.view.alamat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.alamat.AlamatViewModel
import com.example.sikemasapp.data.viewModel.alamat.AlamatViewModelFactory
import com.example.sikemasapp.data.viewModel.ronda.RondaViewModel
import com.example.sikemasapp.data.viewModel.ronda.RondaViewModelFactory
import com.example.sikemasapp.databinding.FragmentAlamatBinding
import com.example.sikemasapp.databinding.FragmentJadwalRondaBinding
import com.example.sikemasapp.ui.component.BlackLoader
import com.example.sikemasapp.ui.view.register.EmailVerificationActivity

class AlamatActivity : AppCompatActivity() {
    private lateinit var binding: FragmentAlamatBinding
    private lateinit var viewModel: AlamatViewModel
    private lateinit var loader: BlackLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, AlamatViewModelFactory(this))
            .get(AlamatViewModel::class.java)
        binding = FragmentAlamatBinding.inflate(layoutInflater)
        loader = BlackLoader(layoutInflater, binding.groupLayout)
        loader.addLoader(binding.root)

        viewModel.getAlamat(""){
            loader.hideLoader()
        }
        loader.showLoader()

        viewModel.alamatRes.observe(this,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loginResult.error?.let {
                    toast(it)
                }
                loginResult.success?.let {

                }
            })

        binding.fab.setOnClickListener {
            loader.showLoader()
            viewModel.getAlamat(binding.search.toString()){
                loader.hideLoader()
            }
        }

        setContentView(binding.root)
    }

    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }
}