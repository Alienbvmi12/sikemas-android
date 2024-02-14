package com.example.sikemasapp.data.viewModel.alarmDarurat

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sikemasapp.R
import com.example.sikemasapp.data.model.http.HttpApi
import com.example.sikemasapp.data.model.http.HttpResponse
import com.example.sikemasapp.data.storage.UserSessionManager
import com.example.sikemasapp.ui.view.alamat.AlamatResult
import com.example.sikemasapp.ui.view.register.LoggedInUserView
import com.example.sikemasapp.ui.view.register.RegisterResult
import com.example.sikemasapp.ui.view.ronda.RondaResult
import com.google.gson.Gson
import kotlinx.coroutines.launch

class AlarmDaruratViewModel(private val context: Context): ViewModel() {

    private var _alarmRes = MutableLiveData<RegisterResult>()
    val alarmRes: LiveData<RegisterResult> = _alarmRes

    private val userSessionManager: UserSessionManager = UserSessionManager(context)

    val itemList: List<AlarmDaruratItem> = listOf<AlarmDaruratItem>(
            AlarmDaruratItem("Pencurian", R.drawable.thief_1),
            AlarmDaruratItem("Kebakaran", R.drawable.fire_1),
            AlarmDaruratItem("Keadaan Darurat", R.drawable.emergency_2),
        )

    val daruratOption = listOf<String>(
        "map", "address"
    )
    fun triggerAlarm(emergency: String, option: Int, location: String, nama: String, id: String, callback: () -> Any){
        viewModelScope.launch {
            try {
                val result = HttpApi.retrofitService.triggerAlarm(
                    userSessionManager.getToken().toString(),
                    mapOf(
                        "event" to emergency,
                        "option" to daruratOption[option],
                        "location" to location,
                        "nama" to nama,
                        "id" to id
                    )
                )
                if(result.isSuccessful){
                    _alarmRes.value = RegisterResult(success = LoggedInUserView(displayName = result.body()!!.message))
                    Log.d("sok", result.body().toString())
                    callback()
                }
                else{
                    Log.d("sok", result.errorBody()!!.string())
                    val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                    _alarmRes.value = RegisterResult(error = responseJson.message)
                    Toast.makeText(context, responseJson.message, Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception){
                _alarmRes.value = RegisterResult(error = "Request Error")
                Toast.makeText(context, "Request Error", Toast.LENGTH_LONG).show()
            }
        }
    }
}