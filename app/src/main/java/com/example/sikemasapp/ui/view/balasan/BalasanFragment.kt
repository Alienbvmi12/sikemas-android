package com.example.sikemasapp.ui.view.alamat

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sikemasapp.data.viewModel.alamat.AlamatViewModel
import com.example.sikemasapp.data.viewModel.alamat.BalasanViewModel
import com.example.sikemasapp.data.viewModel.alamat.BalasanViewModelFactory
import com.example.sikemasapp.data.viewModel.alarmDarurat.AlarmDaruratViewModel
import com.example.sikemasapp.databinding.FragmentAlamatBinding
import com.example.sikemasapp.ui.adapters.AlamatAdapter
import com.example.sikemasapp.ui.component.BlackLoader

class BalasanFragment : Fragment() {
    private lateinit var binding: FragmentAlamatBinding
    private lateinit var viewModel: BalasanViewModel
    private lateinit var loader: BlackLoader

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, BalasanViewModelFactory(requireContext()))
            .get(BalasanViewModel::class.java)
        viewModel.init()
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding = FragmentAlamatBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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