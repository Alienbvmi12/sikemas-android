package com.example.sikemasapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.sikemasapp.databinding.ActivityAuthBinding
import com.example.sikemasapp.ui.view.login.LoginFragment
import com.example.sikemasapp.ui.view.register.RegisterFragment

class AuthActivity: FragmentActivity() {
    private lateinit var binding: ActivityAuthBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: PagerAdapter
    private val NUM_PAGE = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewPager = binding.viewPagerAuth
        pagerAdapter = PagerAdapter(this, NUM_PAGE, viewPager)
        viewPager.adapter = pagerAdapter
    }
}

private class PagerAdapter(
    fm: FragmentActivity,
    private var page: Int,
    private val viewPager: ViewPager2
) : FragmentStateAdapter(fm) {
    override fun getItemCount(): Int = page

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LoginFragment(viewPager)
            1 -> RegisterFragment(viewPager)
            else -> throw IllegalArgumentException("Invalid position")
        }
    }
}