package com.example.sikemasapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.sikemasapp.MainActivity
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.alarmDarurat.TeleponDaruratItem
import com.example.sikemasapp.data.viewModel.main.MainItem

class TelpDaruratRecyclerViewAdapter(private val dataset: List<TeleponDaruratItem>, private val context: Context): RecyclerView.Adapter<TelpDaruratRecyclerViewAdapter.TelpDaruratViewHolder>() {
    private var sepCount = 1

    class TelpDaruratViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val text: TextView = view.findViewById(R.id.item_title)
        val icon: ImageView = view.findViewById(R.id.item_icon)
        val number: TextView = view.findViewById(R.id.item_phone_number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TelpDaruratViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_telepon_darurat, parent, false)
        return TelpDaruratViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: TelpDaruratViewHolder, position: Int) {
        val item = dataset[position]
        holder.text.text = item.text
        holder.icon.setImageResource(item.icon)
        holder.number.text = item.number
        holder.view.setOnClickListener{
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:${item.number}")
            startActivity(this.context, dialIntent, Bundle())
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}