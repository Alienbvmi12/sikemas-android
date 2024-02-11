package com.example.sikemasapp.ui.view.forgot_password

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sikemasapp.AuthActivity
import com.example.sikemasapp.MainActivity2
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.forgotPassword.ForgotPasswordViewModel
import com.example.sikemasapp.data.viewModel.forgotPassword.ForgotPasswordViewModelFactory
import com.example.sikemasapp.databinding.FragmentEmailVerificationBinding
import com.example.sikemasapp.databinding.FragmentForgotPasswordBinding

class ForgotPasswordOtpFragment: Fragment() {
    private lateinit var binding: FragmentEmailVerificationBinding
    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEmailVerificationBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ForgotPasswordViewModelFactory(requireContext()))
            .get(ForgotPasswordViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val loadingProgressBar = binding.loading

        viewModel.otpReqResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    toast(it)
                }
                loginResult.success?.let {
                    toast(it.displayName)
                    findNavController().navigate(R.id.resetPasswordFragment)
                }
            })

        binding.kirimUlangKode.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            viewModel.resendOtp()
        }

        binding.kirimOtp.setOnClickListener{
            loadingProgressBar.visibility = View.VISIBLE
            viewModel.submitOtp(binding.otp.text.toString())
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun toast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_LONG).show()
    }
}