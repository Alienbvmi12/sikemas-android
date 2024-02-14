package com.example.sikemasapp.data.viewModel.kontak_pengaduan

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
import com.example.sikemasapp.ui.view.register.LoggedInUserView
import com.example.sikemasapp.ui.view.register.RegisterResult
import com.example.sikemasapp.ui.view.ronda.RondaResult
import com.google.gson.Gson
import kotlinx.coroutines.launch

class KontakPengaduanViewModel(context: Context): ViewModel() {
    private var _aspRes = MutableLiveData<RegisterResult>()
    val aspRes: LiveData<RegisterResult> = _aspRes

    private val userSessionManager: UserSessionManager = UserSessionManager(context)

    fun postAspirasi(perihal: String, pesan: String, callback: () -> Any){
        viewModelScope.launch {
            try{
                val result = HttpApi.retrofitService.sendAspirasi(
                    userSessionManager.getToken().toString(),
                    mapOf(
                        "id" to userSessionManager.getLoginInfo().getValue("id").toString(),
                        "perihal" to perihal,
                        "pesan" to pesan
                    )
                )
                if(result.isSuccessful){
                    _aspRes.value = RegisterResult(success = LoggedInUserView(result.body()!!.message))
                    callback()
                }
                else{
                    Log.d("tes", result.errorBody()!!.string())
                    val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                    _aspRes.value = RegisterResult(error = responseJson.message)
                }
            } catch (e:Exception){
                _aspRes.value = RegisterResult(error = "Request Error")
            }
        }
    }

}