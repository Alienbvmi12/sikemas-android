package com.example.sikemasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.sikemasapp.data.storage.ApiSessionManager
import com.example.sikemasapp.databinding.ActivitySetApiBinding

class SetApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySetApiBinding.inflate(layoutInflater)
        binding.simpanUrl.setOnClickListener{
            val apiSessionManager = ApiSessionManager(this)
            apiSessionManager.setApiUrl(
                binding.inputUrl.text.toString()
            )
            Toast.makeText(baseContext, "Saved", Toast.LENGTH_SHORT).show()
        }
        setContentView(binding.root)
    }
}