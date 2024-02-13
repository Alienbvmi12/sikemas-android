package com.example.sikemasapp.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sikemasapp.databinding.ItemAlamatBinding

class AlamatAdapter(private val intent: Intent, val goActivity: (Intent) -> Any):
    ListAdapter<Map<String, Any>, AlamatAdapter.MotorcyclesViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full information.
     */
    class MotorcyclesViewHolder(
        var binding: ItemAlamatBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(member: Map<String, Any>) {
            binding.textAlamat.text = member.getValue("alamat").toString()
            binding.textNama.text = member.getValue("nama").toString()
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Map<String, Any>>() {
        override fun areItemsTheSame(oldItem: Map<String, Any>, newItem: Map<String, Any>): Boolean {
            return oldItem.getValue("id") == newItem.getValue("id")
        }

        override fun areContentsTheSame(oldItem: Map<String, Any>, newItem: Map<String, Any>): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MotorcyclesViewHolder {
        return MotorcyclesViewHolder(
            ItemAlamatBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MotorcyclesViewHolder, position: Int) {
        val motor = getItem(position)
        holder.binding.root.setOnClickListener{

        }
        holder.bind(motor)
    }
}