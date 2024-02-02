package com.example.sikemasapp.ui.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sikemasapp.data.viewModel.ronda.MemberItem
import com.example.sikemasapp.databinding.ItemDaysBinding
import com.example.sikemasapp.databinding.ItemMemberBinding

class RondaDetailAdapter():
    ListAdapter<MemberItem, RondaDetailAdapter.RondaDetailViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full information.
     */
    class RondaDetailViewHolder(
        var binding: ItemMemberBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(member: MemberItem) {
            binding.memberData = member
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
    }

    /**
     * Allows the RecyclerView to determine which items have changed when the [List]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<MemberItem>() {
        override fun areItemsTheSame(oldItem: MemberItem, newItem: MemberItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MemberItem, newItem: MemberItem): Boolean {
            return oldItem == newItem
        }
    }

    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RondaDetailViewHolder {
        return RondaDetailViewHolder(
            ItemMemberBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: RondaDetailViewHolder, position: Int) {
        val member = getItem(position)
        Log.e(member.id.toString(), member.name)
        holder.bind(member)
    }
}