package com.example.sikemasapp.data.viewModel.ronda

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sikemasapp.R
import com.example.sikemasapp.data.model.http.HttpApi
import com.example.sikemasapp.data.model.http.HttpResponse
import com.example.sikemasapp.data.model.http.HttpResponseList
import com.example.sikemasapp.data.storage.UserSessionManager
import com.example.sikemasapp.ui.view.ronda.RondaResult
import com.google.gson.Gson
import kotlinx.coroutines.launch

class RondaViewModel(context: Context): ViewModel() {
    val itemList: List<RondaItem> = listOf<RondaItem>(
        RondaItem("Senin", 1),
        RondaItem("Selasa", 2),
        RondaItem("Rabu", 3),
        RondaItem("Kamis", 4),
        RondaItem("Jumat", 5),
        RondaItem("Sabtu", 6),
        RondaItem("Minggu", 7)
    )

    private var _rondaRes = MutableLiveData<RondaResult>()
    val rondaRes: LiveData<RondaResult> = _rondaRes

    private var _memberList = MutableLiveData<List<Map<String, Any>?>>(
        listOf(
            mapOf(
                "id" to "",
                "nama" to "",
                "hari" to "",
                "phone" to "",
                "foto" to ""
            )
        )
    )
    val memberList: LiveData<List<Map<String, Any>?>> = _memberList

    private val userSessionManager: UserSessionManager = UserSessionManager(context)

    fun getJadwalRonda(day: String, callback: () -> Any){
        viewModelScope.launch {
            try{
                val result = HttpApi.retrofitService.getJadwal(day,
                    userSessionManager.getToken().toString()
                )
                if(result.isSuccessful){
                    _rondaRes.value = RondaResult(success = result.body())
                    _memberList.value = result.body()!!.data
                    callback()
                }
                else{
                    Log.d("sok", result.errorBody()!!.string())
                    val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                    _rondaRes.value = RondaResult(error = responseJson.message)
                }
            } catch(e: Exception){
                _rondaRes.value = RondaResult(error = "Request Error")
            }
        }
    }

}