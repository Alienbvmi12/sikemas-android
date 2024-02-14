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
import com.example.sikemasapp.databinding.ItemAlamatBinding
import com.example.sikemasapp.ui.component.DoubleTapGestureListener

class AlamatAdapter(
    private val context: Context,
    private val viewModel: AlarmDaruratViewModel,
    private val intent: Intent,
    private val toast: (s: String) -> Any
): ListAdapter<AlamatItem, AlamatAdapter.MotorcyclesViewHolder>(DiffCallback) {

    /**
     * The MarsPhotosViewHolder constructor takes the binding variable from the associated
     * GridViewItem, which nicely gives it access to the full information.
     */
    class MotorcyclesViewHolder(
        var binding: ItemAlamatBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(
            member: AlamatItem,
            context: Context,
            viewModel: AlarmDaruratViewModel,
            intent: Intent,
            toast: (s: String) -> Any
        ) {
            binding.alamat = member
            var nama = ""
            nama = member.nama ?: "-"
            val gestureDetector = GestureDetector(context, DoubleTapGestureListener(context){
                viewModel.triggerAlarm(
                    intent.getStringExtra("event")!!,
                    1,
                    member.alamat,
                    nama,
                    member.id
                ){
                    toast("Alarm berhasil dibunyikan!!")

                }
            })
            binding.root.setOnTouchListener { _, event ->
                gestureDetector.onTouchEvent(event)
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
    companion object DiffCallback : DiffUtil.ItemCallback<AlamatItem>() {
        override fun areItemsTheSame(oldItem: AlamatItem, newItem: AlamatItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlamatItem, newItem: AlamatItem): Boolean {
            return oldItem.alamat == newItem.alamat
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
        holder.bind(motor, context, viewModel, intent, toast)
    }
}