package com.example.sikemasapp.ui.view.alamat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.data.viewModel.alamat.AlamatViewModel
import com.example.sikemasapp.data.viewModel.alamat.AlamatViewModelFactory
import com.example.sikemasapp.data.viewModel.alamat.BalasanViewModelFactory
import com.example.sikemasapp.data.viewModel.alarmDarurat.AlarmDaruratViewModel
import com.example.sikemasapp.data.viewModel.alarmDarurat.AlarmViewModelFactory
import com.example.sikemasapp.databinding.FragmentAlamatBinding
import com.example.sikemasapp.ui.adapters.AlamatAdapter
import com.example.sikemasapp.ui.component.BlackLoader

class AlamatActivity : AppCompatActivity() {
    private lateinit var binding: FragmentAlamatBinding
    private lateinit var viewModel: AlamatViewModel
    private lateinit var viewModelAlarm: AlarmDaruratViewModel
    private lateinit var loader: BlackLoader
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, AlamatViewModelFactory(this))
            .get(AlamatViewModel::class.java)
        viewModelAlarm = ViewModelProvider(this, AlarmViewModelFactory(this))
            .get(AlarmDaruratViewModel::class.java)

        viewModel.init()
        binding = FragmentAlamatBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.alamatGallery.adapter = AlamatAdapter(this, viewModelAlarm, intent
        ){ message ->
            toast(message)
        }

        loader = BlackLoader(layoutInflater, binding.groupLayout)
        loader.addLoader(binding.root)

        viewModel.getAlamat(""){
            loader.hideLoader()
        }
        loader.showLoader()

        viewModel.alamatRes.observe(this,
            Observer { loginResult ->
                loginResult ?: return@Observer
                loginResult.error?.let {
                    toast(it)
                }
                loginResult.success?.let {

                }
            })

        binding.search.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.getAlamat(binding.search.text.toString()){

                }
            }

        })

        binding.fab.setOnClickListener {
            loader.showLoader()
            viewModel.getAlamat(binding.search.text.toString()){
                loader.hideLoader()
            }
        }

        setContentView(binding.root)
    }

    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }
}