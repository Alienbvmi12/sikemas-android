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
import com.example.sikemasapp.AuthActivity
import com.example.sikemasapp.MainActivity2
import com.example.sikemasapp.data.viewModel.forgotPassword.ForgotPasswordViewModel
import com.example.sikemasapp.data.viewModel.forgotPassword.ForgotPasswordViewModelFactory
import com.example.sikemasapp.databinding.FragmentEmailVerificationBinding
import com.example.sikemasapp.databinding.FragmentForgotPasswordBinding
import com.example.sikemasapp.databinding.FragmentResetPasswordBinding

class ResetPasswordFragment: Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding
    private lateinit var viewModel: ForgotPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResetPasswordBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ForgotPasswordViewModelFactory(requireContext()))
            .get(ForgotPasswordViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val loadingProgressBar = binding.loading2
        val submitPassword = binding.kirimPassword

        viewModel.otpReqResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    toast(it)
                }
                loginResult.success?.let {
                    toast(it.displayName)
                    startActivity(Intent(requireContext(), AuthActivity::class.java))
                }
            })

        submitPassword.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            viewModel.submitPassword(
                binding.password.text.toString(),
                binding.confirmPassword.text.toString()
            )
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun toast(string: String) {
        Toast.makeText(requireContext(), string, Toast.LENGTH_LONG).show()
    }
}