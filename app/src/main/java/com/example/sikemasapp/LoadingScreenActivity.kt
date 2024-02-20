package com.example.sikemasapp

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.GestureDetector
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.sikemasapp.data.storage.RegisterSessionManager
import com.example.sikemasapp.data.storage.RegisterSessionManager.Companion.EMAIL_VERIFICATION_PHASE
import com.example.sikemasapp.process.work_manager.NotificationWorker
import com.example.sikemasapp.ui.component.DoubleTapGestureListener
import com.example.sikemasapp.ui.view.register.EmailVerificationActivity
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import java.util.concurrent.TimeUnit

@Suppress("DEPRECATION")
class LoadingScreenActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val registerSessionManager = RegisterSessionManager(this)
        setContentView(R.layout.activity_loading_screen)

//        val gestureDetector = GestureDetector(this, DoubleTapGestureListener(this){
//            startActivity(Intent(this, SetApiActivity::class.java))
//        })
//
//        findViewById<ImageView>(R.id.imageView2).setOnClickListener{
//            startActivity(Intent(this, SetApiActivity::class.java))
//        }


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        Firebase.messaging.subscribeToTopic("emergency")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
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
                }, 1000)
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(ContentValues.TAG, msg)
            }

        // Create a periodic work request (runs every 15 minutes)
        val notificationWorkRequest =
            PeriodicWorkRequestBuilder<NotificationWorker>(1, TimeUnit.MINUTES)
                .build()

        // Enqueue the work request
        WorkManager.getInstance(this).enqueue(notificationWorkRequest)

    }
}