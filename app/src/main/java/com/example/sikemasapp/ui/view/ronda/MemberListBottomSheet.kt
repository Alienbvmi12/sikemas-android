package com.example.sikemasapp.ui.view.ronda

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.ronda.RondaViewModel
import com.example.sikemasapp.databinding.FragmentRondaDetailBinding
import com.example.sikemasapp.ui.adapters.RondaDetailAdapter
import com.example.sikemasapp.ui.adapters.RondaRecyclerViewAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class MemberListBottomSheet(val viewModel: RondaViewModel): BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ronda_detail, container, false)
        view.findViewById<RecyclerView>(R.id.ronda_detail_recyclerview).adapter = RondaDetailAdapter()
        return view
    }
}