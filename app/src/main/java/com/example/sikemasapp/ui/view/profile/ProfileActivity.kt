package com.example.sikemasapp.ui.view.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.net.toUri
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.sikemasapp.AuthActivity
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.login.LoginViewModel
import com.example.sikemasapp.data.viewModel.login.LoginViewModelFactory
import com.example.sikemasapp.data.viewModel.profile.ProfileViewModel
import com.example.sikemasapp.data.viewModel.profile.ProfileViewModelFactory
import com.example.sikemasapp.databinding.FragmentLoginBinding
import com.example.sikemasapp.databinding.FragmentProfileBinding
import com.example.sikemasapp.databinding.FragmentProfileDetailBinding
import com.example.sikemasapp.ui.adapters.ProfileDetailAdapter
import com.example.sikemasapp.ui.component.BlackLoader
import com.example.sikemasapp.ui.view.ronda.MemberListBottomSheet
import com.google.android.material.bottomsheet.BottomSheetDialog

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, ProfileViewModelFactory(this))
            .get(ProfileViewModel::class.java)

        val loader = BlackLoader(layoutInflater, binding.root)
        loader.addLoader(binding.root)

        binding.logoutButton.setOnClickListener {
            loader.showLoader()
            viewModel.logout{
                loader.hideLoader()
                startActivity(Intent(this, AuthActivity::class.java))
            }
        }


        viewModel.userData.observe(this,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loader.hideLoader()
                loginResult.error?.let {
                    toast(it)
                }
                loginResult.success?.let {
                    binding.profileNik.text = it.data.getValue("nik").toString()
                    binding.profileEmail.text = it.data.getValue("email").toString()
                    binding.profilePhone.text = it.data.getValue("phone").toString()
                    binding.profileUsername.text = it.data.getValue("username").toString()
                    binding.profileRole.text = "(" + it.data.getValue("posisi").toString() + ")"

                    val imgUrl = it.data.getValue("foto").toString()
                    val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
                    binding.imageView5.load(imgUri) {
                        placeholder(R.drawable.loading_animation)
                        error(com.google.android.material.R.drawable.mtrl_ic_error)
                    }
                }

                binding.selengkapnya.setOnClickListener {
                    val bottomSheetFragment = BottomSheetDialog(this)

                    val binding = FragmentProfileDetailBinding.inflate(layoutInflater)
                    binding.viewModel = viewModel

                    binding.daysRecyclerView.adapter = ProfileDetailAdapter()
                    binding.imageView9.load(viewModel.userData.value?.success?.data?.getValue("foto").toString()){
                        placeholder(R.drawable.loading_animation)
                        error(com.google.android.material.R.drawable.mtrl_ic_error)
                    }

                    bottomSheetFragment.setContentView(binding.root)
                    bottomSheetFragment.show()
                }
            }
        )

        loader.showLoader()
        viewModel.getProfile()
        setContentView(binding.root)
    }

    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }
}