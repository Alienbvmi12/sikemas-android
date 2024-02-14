package com.example.sikemasapp.ui.adapters

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sikemasapp.data.viewModel.alamat.AlamatItem
import com.example.sikemasapp.data.viewModel.balasan.BalasanItem


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
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Map<String, Any>?>?){
    val adapter = recyclerView.adapter as RondaDetailAdapter
    adapter.submitList(data)
}
@BindingAdapter("listData3")
fun bindRecyclerView3(recyclerView: RecyclerView, data: List<AlamatItem>){
    val adapter = recyclerView.adapter as AlamatAdapter
    adapter.submitList(data)
}

@BindingAdapter("listData4")
fun bindRecyclerView4(recyclerView: RecyclerView, data: List<BalasanItem>){
    val adapter = recyclerView.adapter as BalasanAdapter
    adapter.submitList(data)
}

@BindingAdapter("setAlamat")
fun bindAlamat(txt: TextView, str: AlamatItem) {
    txt.text = str.alamat
}

@BindingAdapter("setNama")
fun bindNama(txt: TextView, str: AlamatItem) {
    val value = str.nama
    if(value != null){
        txt.text = value
    }
    else{
        txt.text = "-"
    }
}
