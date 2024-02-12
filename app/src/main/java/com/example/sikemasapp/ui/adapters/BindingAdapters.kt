package com.example.sikemasapp.ui.adapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView


//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<MemberItem>){
//    val adapter = recyclerView.adapter as RondaDetailAdapter
//    adapter.submitList(data)
//}

@BindingAdapter("listData2")
fun bindRecyclerView2(recyclerView: RecyclerView, data: List<Map<String, Any>?>?){
    val adapter = recyclerView.adapter as ProfileDetailAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Map<String, Any>>){
    val adapter = recyclerView.adapter as RondaDetailAdapter
    adapter.submitList(data)
}