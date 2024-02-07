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
import com.example.sikemasapp.databinding.FragmentLoginBinding
import com.example.sikemasapp.databinding.FragmentRegisterBinding
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment(private val viewPager: ViewPager2) : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
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
//        loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
//            .get(LoginViewModel::class.java)
        val navToLogin = binding.loginRegister

        navToLogin.setOnClickListener {
            viewPager.setCurrentItem(0, true)
        }

    }

}