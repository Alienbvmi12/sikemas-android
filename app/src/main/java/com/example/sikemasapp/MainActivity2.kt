package com.example.sikemasapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.view.Window
import android.widget.Toolbar
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.navigateUp
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.sikemasapp.data.viewModel.main.MainActivityViewModel
import com.example.sikemasapp.databinding.ActivityMainBinding
import com.example.sikemasapp.ui.adapters.MainRecyclerViewAdapter
import com.example.sikemasapp.ui.adapters.MainRecyclerViewAdapter2
import com.example.sikemasapp.ui.view.profile.ProfileActivity
import com.google.android.material.appbar.MaterialToolbar

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var slidingPaneLayout: SlidingPaneLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val appbar = binding.includeLayoutAppbar
        val content = appbar.includeContentMain
        slidingPaneLayout = content.slidingPaneLayout

        content.fmainInclude.mainOptioRecyclerview
            .adapter = MainRecyclerViewAdapter2(viewModel.itemList2, this) { intege ->
            findNavController(R.id.nav_host_fragment_content_homies).navigate(intege)
            slidingPaneLayout.openPane()
        }

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