package com.example.sikemasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Toolbar
import androidx.activity.viewModels
import com.example.sikemasapp.data.viewModel.main.MainActivityViewModel
import com.example.sikemasapp.databinding.ActivityMainBinding
import com.example.sikemasapp.ui.adapters.MainRecyclerViewAdapter
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val appbar = binding.includeLayoutAppbar
        val appContent = appbar.includeContentMain
        appContent.mainOptionRecyclerview
            .adapter = MainRecyclerViewAdapter(viewModel.itemList, this)

        this.requestWindowFeature(Window.FEATURE_NO_TITLE)

        setSupportActionBar(appbar.toolbar)

        supportActionBar?.title = ""

        val toolbar: MaterialToolbar = appbar.toolbar

        val profileInfoView: View = layoutInflater.inflate(R.layout.layout_profile_info, null)
        toolbar.addView(profileInfoView)

        setContentView(binding.root)
    }
}