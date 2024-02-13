package com.example.sikemasapp.ui.view.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.MainActivity2
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.register.RegisterViewModel
import com.example.sikemasapp.data.viewModel.register.RegisterViewModelFactory
import com.example.sikemasapp.databinding.FragmentEmailVerificationBinding
import com.example.sikemasapp.databinding.FragmentRegisterBinding
import com.example.sikemasapp.ui.component.BlackLoader

private lateinit var viewModel: RegisterViewModel
private var _binding: FragmentRegisterBinding? = null

class EmailVerificationActivity : AppCompatActivity() {
    private lateinit var binding: FragmentEmailVerificationBinding
    private lateinit var loader: BlackLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, RegisterViewModelFactory(this))
            .get(RegisterViewModel::class.java)
        binding = FragmentEmailVerificationBinding.inflate(layoutInflater)
        loader = BlackLoader(layoutInflater, binding.root)
        loader.addLoader(binding.root)
        val kirimButton = binding.kirimOtp
        val kolomOtp = binding.otp
        val loadingProgressBar = binding.loading

        viewModel.registerResult.observe(this,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loader.hideLoader()
                loginResult.error?.let {
                    toast(it)
                }
                loginResult.success?.let {
                    startActivity(Intent(this, MainActivity2::class.java))
                }
            })

        viewModel.resendResult.observe(this,
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

        kirimButton.setOnClickListener{
            loader.showLoader()
            viewModel.submitOtp(kolomOtp.text.toString())
        }
        binding.kirimUlangKode.setOnClickListener {
            loader.showLoader()
            viewModel.resendOtp()
        }

        setContentView(binding.root)
    }

    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }
}