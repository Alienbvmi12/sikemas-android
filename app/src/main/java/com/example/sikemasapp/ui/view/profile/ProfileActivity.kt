package com.example.sikemasapp.ui.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.AuthActivity
import com.example.sikemasapp.data.viewModel.login.LoginViewModel
import com.example.sikemasapp.data.viewModel.login.LoginViewModelFactory
import com.example.sikemasapp.data.viewModel.profile.ProfileViewModel
import com.example.sikemasapp.data.viewModel.profile.ProfileViewModelFactory
import com.example.sikemasapp.databinding.FragmentLoginBinding
import com.example.sikemasapp.databinding.FragmentProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ProfileViewModelFactory(this))
            .get(ProfileViewModel::class.java)
        binding.logoutButton.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this, AuthActivity::class.java))
        }
        setContentView(binding.root)
    }
}