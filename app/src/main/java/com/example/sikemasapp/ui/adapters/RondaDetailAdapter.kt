package com.example.sikemasapp.ui.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.sikemasapp.R
import com.example.sikemasapp.databinding.ItemDaysBinding
import com.example.sikemasapp.databinding.ItemMemberBinding
import com.example.sikemasapp.ui.view.profile.ProfileActivity

class RondaDetailAdapter(val context: Context):
    ListAdapter<Map<String, Any>, RondaDetailAdapter.RondaDetailViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full information.
     */
    class RondaDetailViewHolder(
        var binding: ItemMemberBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(member: Map<String, Any?>, context: Context) {
            binding.root.setOnClickListener{
//                val intent = Intent(context, ProfileActivity::class.java)
//                intent.putExtra("id", member.getValue("id").toString())
//                context.startActivity(intent)
            }
            binding.memberName.text = member.getValue("nama").toString()
            binding.memberPhone.text = member.getValue("phone").toString()
            val imgUrl = member.getValue("foto").toString()
            val imgUri = imgUrl.toUri().buildUpon().scheme("http").build()
            binding.memberPhoto.load(imgUri) {
                placeholder(R.drawable.loading_animation)
                error(com.google.android.material.R.drawable.mtrl_ic_error)
            }
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
        holder.bind(member, context)
    }
}