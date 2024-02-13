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
import com.example.sikemasapp.ui.view.ronda.RondaResult
import com.google.gson.Gson
import kotlinx.coroutines.launch

class AlamatViewModel(context: Context): ViewModel() {

    private var _alamatRes = MutableLiveData<RondaResult>()
    val alamatRes: LiveData<RondaResult> = _alamatRes

    private var _memberList = MutableLiveData<List<Map<String, Any>>>(
        listOf(
            mapOf(
                "id" to "",
                "nama" to "",
                "alamat" to ""
            )
        )
    )
    val memberList: LiveData<List<Map<String, Any>>> = _memberList

    private val userSessionManager: UserSessionManager = UserSessionManager(context)

    fun getAlamat(keyword: String, callback: () -> Any){
        viewModelScope.launch {
            val result = HttpApi.retrofitService.getAlamat(
                keyword,
                userSessionManager.getToken().toString()
            )
            if(result.isSuccessful){
                _alamatRes.value = RondaResult(success = result.body())
                _memberList.value = result.body()!!.data
            }
            else{
                Log.d("sok", result.errorBody()!!.string())
                val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                _alamatRes.value = RondaResult(error = responseJson.message)
            }
            callback()
        }
    }

}