package com.example.sikemasapp.ui.view.forgot_password

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sikemasapp.R
import com.example.sikemasapp.databinding.ActivityForgotPasswordBinding

class ForgotPasswordActivity : AppCompatActivity() {
    lateinit var binding: ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}