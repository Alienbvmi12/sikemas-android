package com.example.sikemasapp.data.viewModel.teleponDarurat

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sikemasapp.R

class TeleponDaruratViewModel(private val context: Context): ViewModel() {
    val itemList: List<TeleponDaruratItem> = listOf<TeleponDaruratItem>(
        TeleponDaruratItem("Nomor Darurat (umum)", R.drawable.alert_128, context.getString(R.string.no_darurat)),
        TeleponDaruratItem("Pemadam Kebakaran", R.drawable.firefighter_pngrepo_com, context.getString(R.string.no_damkar)),
        TeleponDaruratItem("Ambulan", R.drawable.nurse_pngrepo_com, context.getString(R.string.no_ambulan)),
        TeleponDaruratItem("Polisi", R.drawable.policeman_pngrepo_com, context.getString(R.string.no_polisi)),
        TeleponDaruratItem("Tim SAR", R.drawable.sar, context.getString(R.string.no_sar))
        )
//    private fun openPhone(number: String){
//        val dialIntent = Intent(Intent.ACTION_DIAL)
//        dialIntent.data = Uri.parse("tel:$number")
//        startActivity(this.context, dialIntent, Bundle())
//    }
}