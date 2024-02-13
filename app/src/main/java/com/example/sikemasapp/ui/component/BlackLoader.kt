package com.example.sikemasapp.ui.component

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.sikemasapp.R

class BlackLoader(
    val inflater: LayoutInflater,
    val container: ViewGroup?,
) {
    private lateinit var loaderOverlay: View

    fun addLoader(rootView: View?){
        loaderOverlay = inflater.inflate(R.layout.layout_overlay_loader, container, false)
        loaderOverlay.visibility = View.GONE // Initially hide the loader
        // Add the loader overlay to the root view
        (rootView as ViewGroup).addView(loaderOverlay)
        // Set click listener for the loader overlay
        loaderOverlay.setOnClickListener {
            // Do nothing to prevent clicks on the overlay while the loader is visible
        }
    }

    fun showLoader() {
        loaderOverlay.visibility = View.VISIBLE
        loaderOverlay.findViewById<ProgressBar>(R.id.loaderProgressBar).visibility = View.VISIBLE
    }

    fun hideLoader() {
        loaderOverlay.visibility = View.GONE
        loaderOverlay.findViewById<ProgressBar>(R.id.loaderProgressBar).visibility = View.GONE
    }
}