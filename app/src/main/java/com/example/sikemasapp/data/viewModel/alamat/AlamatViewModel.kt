package com.example.sikemasapp.data.viewModel.alamat

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sikemasapp.data.model.http.HttpApi
import com.example.sikemasapp.data.model.http.HttpResponse
import com.example.sikemasapp.data.storage.UserSessionManager
import com.example.sikemasapp.ui.view.alamat.AlamatResult
import com.example.sikemasapp.ui.view.ronda.RondaResult
import com.google.gson.Gson
import com.squareup.moshi.Json
import kotlinx.coroutines.launch

class AlamatViewModel(context: Context): ViewModel() {

    private var _alamatRes = MutableLiveData<AlamatResult>()
    val alamatRes: LiveData<AlamatResult> = _alamatRes

    private var _memberList = MutableLiveData<List<AlamatItem>>()
    val memberList: LiveData<List<AlamatItem>> = _memberList

    private val userSessionManager: UserSessionManager = UserSessionManager(context)

    fun getAlamat(keyword: String, callback: () -> Any){
        viewModelScope.launch {
            try {
                val result = HttpApi.retrofitService.getAlamat(
                    keyword,
                    userSessionManager.getToken().toString()
                )
                if(result.isSuccessful){
                    _alamatRes.value = AlamatResult(success = result.body())
                    Log.d("sok", result.body().toString())
                    _memberList.value = result.body()!!.data
                }
                else{
                    Log.d("sok", result.errorBody()!!.string())
                    val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                    _alamatRes.value = AlamatResult(error = responseJson.message)
                }
                callback()
            } catch (e: Exception){
                _alamatRes.value = AlamatResult(error = "Request Error")
            }
        }
    }

    fun init(){
        _memberList.value = listOf<AlamatItem>()
    }
}

data class AlamatItem(
    val id : String,
    val alamat: String,
    val nama: String?,
    @Json(name = "warga_id") val wargaId: String?
)