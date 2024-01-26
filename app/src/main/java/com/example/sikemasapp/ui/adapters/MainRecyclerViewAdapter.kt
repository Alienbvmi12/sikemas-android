package com.example.sikemasapp.ui.adapters

import android.content.Context
import android.content.Intent
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
import com.example.sikemasapp.data.viewModel.main.MainItem

class MainRecyclerViewAdapter(private val dataset: List<MainItem>, private val context: Context): RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {
    private var sepCount = 1

    class MainRecyclerViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val text: TextView = view.findViewById(R.id.textView4)
        val icon: ImageView = view.findViewById(R.id.imageView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return MainRecyclerViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        val item = dataset[position]
        holder.text.text = item.text
        holder.icon.setImageResource(item.icon)
        if(position + 1 !== 1){
//            if((position + 1) % 2 === 0){
            if(sepCount <= 2 && sepCount > 0) {
                holder.view.background = ContextCompat.getDrawable(context, R.drawable.main_item_shape_2)
                if(sepCount === 2) sepCount = 0
            }
            Log.d("Sepcount", "sepCount = $sepCount")
            if(sepCount > 0){
                sepCount++
            }
            else {
                sepCount--
            }
            if(sepCount === -3) sepCount = 1
//            }
        }
        holder.view.setOnClickListener{
            val intent = Intent(context, item.clas)
            startActivity(context, intent, Bundle())
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}