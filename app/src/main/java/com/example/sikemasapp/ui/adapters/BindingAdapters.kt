package com.example.sikemasapp.ui.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sikemasapp.data.viewModel.ronda.MemberItem

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MemberItem>){
    val adapter = recyclerView.adapter as RondaDetailAdapter
    adapter.submitList(data)
}