package com.example.sikemasapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sikemasapp.data.viewModel.alamat.AlamatItem
import com.example.sikemasapp.data.viewModel.alarmDarurat.AlarmDaruratViewModel
import com.example.sikemasapp.data.viewModel.balasan.BalasanItem
import com.example.sikemasapp.data.viewModel.balasan.BalasanViewModel
import com.example.sikemasapp.databinding.ItemAlamatBinding
import com.example.sikemasapp.databinding.ItemBalasanBinding
import com.example.sikemasapp.ui.component.DoubleTapGestureListener

class BalasanAdapter(
    private val context: Context,
    private val viewModel: BalasanViewModel,
    private val toast: (s: String) -> Any
): ListAdapter<BalasanItem, BalasanAdapter.MotorcyclesViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full information.
     */
    class MotorcyclesViewHolder(
        var binding: ItemBalasanBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            member: BalasanItem,
            context: Context,
            viewModel: BalasanViewModel,
            toast: (s: String) -> Any
        ) {
            binding.balasan = member
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<BalasanItem>() {
        override fun areItemsTheSame(oldItem: BalasanItem, newItem: BalasanItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: BalasanItem, newItem: BalasanItem): Boolean {
            return oldItem.body == newItem.body
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
            ItemBalasanBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: MotorcyclesViewHolder, position: Int) {
        val motor = getItem(position)
        holder.binding.root.setOnClickListener{

        }
        holder.bind(motor, context, viewModel, toast)
    }
}