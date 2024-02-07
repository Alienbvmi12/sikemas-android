package com.example.sikemasapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.alarmDarurat.AlarmDaruratItem

class AlarmDaruratRecyclerViewAdapter(
    private val dataset: List<AlarmDaruratItem>,
    private val context: Context,
    private val showLocation: () -> Any
):
    RecyclerView.Adapter<AlarmDaruratRecyclerViewAdapter.AlarmDaruratViewHolder>() {

    class AlarmDaruratViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val text: TextView = view.findViewById(R.id.item_title2)
        val icon: ImageView = view.findViewById(R.id.item_icon2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmDaruratViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_alarm_darurat, parent, false)
        return AlarmDaruratViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: AlarmDaruratViewHolder, position: Int) {
        val item = dataset[position]
        holder.text.text = item.text
        holder.icon.setImageResource(item.icon)
        holder.view.setOnClickListener{
            showLocation()
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}