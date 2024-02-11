package com.example.sikemasapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.sikemasapp.data.storage.RegisterSessionManager
import com.example.sikemasapp.data.storage.RegisterSessionManager.Companion.EMAIL_VERIFICATION_PHASE
import com.example.sikemasapp.ui.view.register.EmailVerificationActivity

@Suppress("DEPRECATION")
class LoadingScreenActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val registerSessionManager = RegisterSessionManager(this)
        setContentView(R.layout.activity_loading_screen)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Handler(Looper.getMainLooper()).postDelayed({
            var intent: Intent
            if(registerSessionManager.getPhaseInfo() === EMAIL_VERIFICATION_PHASE){
                intent = Intent(this, EmailVerificationActivity::class.java)
            }
            else{
                intent = Intent(this, AuthActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 2000)
    }
}