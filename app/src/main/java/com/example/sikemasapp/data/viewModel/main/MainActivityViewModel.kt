package com.example.sikemasapp.data.viewModel.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sikemasapp.R
import com.example.sikemasapp.data.model.http.BASE_URL
import com.example.sikemasapp.data.storage.ApiSessionManager
import com.example.sikemasapp.data.storage.UserSessionManager

class MainActivityViewModel(context: Context): ViewModel() {
    val userSessionManager: UserSessionManager = UserSessionManager(context)
    val userData = userSessionManager.getLoginInfo()

    val itemList2: List<MainItem2> = listOf<MainItem2>(
        MainItem2("Alarm Darurat", R.drawable.alarm_clock_1, R.id.alarmDaruratFragment),
        MainItem2("Telepon Darurat", R.drawable.telephone_call_1, R.id.teleponDaruratFragment),
        MainItem2("Kontak Pengaduan", R.drawable.report_1, R.id.kontakPengaduanFragment),
        MainItem2("Jadwal Ronda", R.drawable.schedule_1, R.id.rondaFragment),
        MainItem2("Balasan Pengaduan", R.drawable.file_1, R.id.balasanFragment),
    )
}