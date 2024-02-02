package com.example.sikemasapp.data.viewModel.alarmDarurat

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sikemasapp.R

class AlarmDaruratViewModel(): ViewModel() {
    val itemList: List<AlarmDaruratItem> = listOf<AlarmDaruratItem>(
            AlarmDaruratItem("Pencurian", R.drawable.alarm_clock_1),
            AlarmDaruratItem("Kebakaran", R.drawable.alarm_clock_1),
            AlarmDaruratItem("Darurat!! Berkumpul", R.drawable.alarm_clock_1),
        )
//    private fun openPhone(number: String){
//        val dialIntent = Intent(Intent.ACTION_DIAL)
//        dialIntent.data = Uri.parse("tel:$number")
//        startActivity(this.context, dialIntent, Bundle())
//    }
}