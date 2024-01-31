package com.example.sikemasapp.data.viewModel.main

import androidx.lifecycle.ViewModel
import com.example.sikemasapp.MainActivity
import com.example.sikemasapp.R
import com.example.sikemasapp.ui.view.telepon_darurat.TeleponDaruratActivity

class MainActivityViewModel: ViewModel() {
    val itemList: List<MainItem> = listOf<MainItem>(
        MainItem("Alarm Darurat", R.drawable.alarm_clock_1, MainActivity::class.java),
        MainItem("Telepon Darurat", R.drawable.telephone_call_1, TeleponDaruratActivity::class.java),
        MainItem("Kontak Pengaduan", R.drawable.report_1, MainActivity::class.java),
        MainItem("Jadwal Ronda", R.drawable.schedule_1, MainActivity::class.java),
        MainItem("Struktur Organigram", R.drawable.diagram_1, MainActivity::class.java),
        MainItem("Catatan Kejadian", R.drawable.file_1, MainActivity::class.java),
    )
}