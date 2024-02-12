package com.example.sikemasapp.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sikemasapp.databinding.ItemDaysBinding
import com.example.sikemasapp.databinding.ItemMemberBinding
import com.example.sikemasapp.databinding.ItemProfileDetailBinding
class ProfileDetailAdapter():
    ListAdapter<Map<String, Any>, ProfileDetailAdapter.ProfileDetailViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full information.
     */
    class ProfileDetailViewHolder(
        var binding: ItemProfileDetailBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(profileItem: Map<String, Any>, number: Int) {
            binding.textView5.text = number.toString() + ". " + profileItem.keys.toList()[0]
            binding.textView16.text = profileItem.values.toList()[0].toString()
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
            return oldItem == newItem
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
    ): ProfileDetailViewHolder {
        return ProfileDetailViewHolder(
            ItemProfileDetailBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: ProfileDetailViewHolder, position: Int) {
        val member = getItem(position)
        holder.bind(member, position + 1)
    }
}