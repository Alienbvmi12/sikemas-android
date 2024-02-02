package com.example.sikemasapp.ui.view.ronda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.sikemasapp.data.viewModel.ronda.RondaViewModel
import com.example.sikemasapp.databinding.FragmentJadwalRondaBinding
import com.example.sikemasapp.databinding.FragmentKontakPengaduanBinding
import com.example.sikemasapp.databinding.FragmentLoginBinding
import com.example.sikemasapp.ui.adapters.RondaRecyclerViewAdapter

class RondaFragment : Fragment() {
    private lateinit var binding: FragmentJadwalRondaBinding
    private val viewModel: RondaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentJadwalRondaBinding.inflate(inflater, container, false)
        binding.daysRecyclerView.adapter = RondaRecyclerViewAdapter(viewModel.itemList, requireFragmentManager(), viewModel)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}