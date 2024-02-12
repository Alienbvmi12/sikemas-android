package com.example.sikemasapp.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.sikemasapp.R
import com.example.sikemasapp.data.viewModel.ronda.RondaItem
import com.example.sikemasapp.data.viewModel.ronda.RondaViewModel
import com.example.sikemasapp.ui.view.ronda.MemberListBottomSheet

class RondaRecyclerViewAdapter(
    private val dataset: List<RondaItem>,
    private val fragmentManager: FragmentManager,
    private val viewModel: RondaViewModel,
    private val lifecycleOwner: LifecycleOwner,
    private val context: Context
): RecyclerView.Adapter<RondaRecyclerViewAdapter.RondaViewHolder>() {
    private var sepCount = 1

    class RondaViewHolder(val view: View): RecyclerView.ViewHolder(view){
        val dayName: TextView = view.findViewById(R.id.dayName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RondaViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_days, parent, false)
        return RondaViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: RondaViewHolder, position: Int) {
        val item = dataset[position]
        holder.dayName.text = item.dayName
        holder.view.setOnClickListener{
            val dayIndex = item.dayIndex
//            viewModel.rondaRes.observe(lifecycleOwner,
//                Observer { rondaResult ->
//                    rondaResult ?: return@Observer
//                    rondaResult.error?.let {
//                        toast(it)
//                    }
//                    rondaResult.success?.let {
//
//                    }
//                }
//            )
            viewModel.getJadwalRonda(dayIndex.toString()){
                val bottomSheetFragment = MemberListBottomSheet(viewModel, item.dayName)
                bottomSheetFragment.show(fragmentManager, bottomSheetFragment.tag)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    private fun toast(string: String) {
        Toast.makeText(context, string, Toast.LENGTH_LONG).show()
    }

}