package com.example.sikemasapp.ui.view.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sikemasapp.databinding.FragmentProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}