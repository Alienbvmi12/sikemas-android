package com.example.sikemasapp

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.sikemasapp.data.storage.RegisterSessionManager
import com.example.sikemasapp.data.viewModel.forgotPassword.ForgotPasswordViewModel
import com.example.sikemasapp.data.viewModel.forgotPassword.ForgotPasswordViewModelFactory
import com.example.sikemasapp.data.viewModel.main.MainActivityViewModel
import com.example.sikemasapp.data.viewModel.main.MainViewModelFactory
import com.example.sikemasapp.databinding.ActivityMainBinding
import com.example.sikemasapp.ui.adapters.MainRecyclerViewAdapter2
import com.example.sikemasapp.ui.view.alarm.AlarmDaruratFragment
import com.example.sikemasapp.ui.view.profile.ProfileActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var slidingPaneLayout: SlidingPaneLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, MainViewModelFactory(this))
            .get(MainActivityViewModel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val appbar = binding.includeLayoutAppbar
        val content = appbar.includeContentMain
        slidingPaneLayout = content.slidingPaneLayout

        content.fmainInclude.mainOptioRecyclerview
            .adapter = MainRecyclerViewAdapter2(viewModel.itemList2, this) { intege ->
            findNavController(R.id.nav_host_fragment_content_homies).navigate(intege)
            slidingPaneLayout.openPane()
        }

        //Setup firebase message broadcast

        Firebase.messaging.subscribeToTopic("emergency")
            .addOnCompleteListener { task ->
                var msg = "Subscribed"
                if (!task.isSuccessful) {
                    msg = "Subscribe failed"
                }
                Log.d(TAG, msg)
                Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            }

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, "FirebaseChannel")
            .setSmallIcon(R.drawable.alarm_1)
            .setContentTitle("TEST")
            .setContentText("TEST")
            .setAutoCancel(true)

        val manager: NotificationManagerCompat = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        manager.notify(1001, builder.build())

        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        setSupportActionBar(appbar.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.title = ""
        appbar.toolbar.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        val toolbar: MaterialToolbar = appbar.toolbar

        val profileInfoView: View = layoutInflater.inflate(R.layout.layout_profile_info, null)
        toolbar.addView(profileInfoView)

        profileInfoView.findViewById<ImageView>(R.id.profileImageView).setImageResource(R.drawable.nurse_pngrepo_com)
        profileInfoView.findViewById<TextView>(R.id.usernameTextView).text = viewModel.userData.getValue("username").toString()
        profileInfoView.findViewById<TextView>(R.id.roleTextView).text = viewModel.userData.getValue("email").toString()

        slidingPaneLayout.setPanelSlideListener(object : SlidingPaneLayout.PanelSlideListener {
            override fun onPanelSlide(panel: View, slideOffset: Float) {

            }

            override fun onPanelOpened(panel: View) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }

            override fun onPanelClosed(panel: View) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
        })

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        if (isRightPaneOpened()) {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_homies)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun isRightPaneOpened(): Boolean {
        return slidingPaneLayout.isSlideable && slidingPaneLayout.isOpen()
    }

    override fun onBackPressed() {
        if(false){
            super.onBackPressed()
        }
        if(isRightPaneOpened()){
            slidingPaneLayout.closePane()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Handle the Up button press (e.g., navigate back or custom behavior)
                if(isRightPaneOpened()){
                    slidingPaneLayout.closePane()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}