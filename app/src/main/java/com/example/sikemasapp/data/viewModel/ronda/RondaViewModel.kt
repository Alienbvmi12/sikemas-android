package com.example.sikemasapp.data.viewModel.ronda

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.sikemasapp.R

class RondaViewModel(): ViewModel() {
    val itemList: List<RondaItem> = listOf<RondaItem>(
        RondaItem("Senin", 1),
        RondaItem("Selasa", 2),
        RondaItem("Rabu", 3),
        RondaItem("Kamis", 4),
        RondaItem("Jumat", 5),
        RondaItem("Sabtu", 6),
        RondaItem("Minggu", 0)
    )

    var memberList: List<MemberItem> = listOf<MemberItem>(
        MemberItem(1, "Wiranto"),
        MemberItem(2, "Agus"),
        MemberItem(3, "Saepul"),
        MemberItem(4, "Roger"),
        MemberItem(5, "Yamaguchi")
    )

}