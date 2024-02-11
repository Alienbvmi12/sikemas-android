package com.example.sikemasapp.ui.view.register

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.sikemasapp.MainActivity2
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.login.LoginViewModel
import com.example.sikemasapp.data.viewModel.login.LoginViewModelFactory
import com.example.sikemasapp.data.viewModel.register.RegisterViewModel
import com.example.sikemasapp.data.viewModel.register.RegisterViewModelFactory
import com.example.sikemasapp.databinding.FragmentLoginBinding
import com.example.sikemasapp.databinding.FragmentRegisterBinding
import com.example.sikemasapp.ui.view.login.LoggedInUserView
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment(private val viewPager: ViewPager2) : Fragment() {

    private lateinit var viewModel: RegisterViewModel
    private var _binding: FragmentRegisterBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, RegisterViewModelFactory(requireContext()))
            .get(RegisterViewModel::class.java)
        val navToLogin = binding.loginRegister
        val registerButton = binding.register
        val loadingProgressBar = binding.loading

        navToLogin.setOnClickListener {
            viewPager.setCurrentItem(0, true)
        }

        viewModel.registerResult.observe(viewLifecycleOwner,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loadingProgressBar.visibility = View.GONE
                loginResult.error?.let {
                    showLoginFailed(it)
                }
                loginResult.success?.let {
                    startActivity(Intent(requireContext(), EmailVerificationActivity::class.java))
                }
            })

        registerButton.setOnClickListener {
            loadingProgressBar.visibility = View.VISIBLE
            viewModel.register(
                binding.insertNik.text.toString(),
                binding.insertEmail.text.toString(),
                binding.insertUsername.text.toString(),
                binding.insertPassword.text.toString(),
                binding.insertConfirmPassword.text.toString()
            )
        }

    }

    private fun showLoginFailed(errorString: String) {
        val appContext = context?.applicationContext ?: return
        Toast.makeText(appContext, errorString, Toast.LENGTH_LONG).show()
    }

}