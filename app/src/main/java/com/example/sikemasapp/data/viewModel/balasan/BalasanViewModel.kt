package com.example.sikemasapp.data.viewModel.balasan

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
import com.example.sikemasapp.ui.view.balasan.BalasanResult
import com.google.gson.Gson
import com.squareup.moshi.Json
import kotlinx.coroutines.launch

class BalasanViewModel(context: Context): ViewModel() {

    private var _alamatRes = MutableLiveData<BalasanResult>()
    val alamatRes: LiveData<BalasanResult> = _alamatRes

    private var _memberList = MutableLiveData<List<BalasanItem>>()
    val memberList: LiveData<List<BalasanItem>> = _memberList

    private val userSessionManager: UserSessionManager = UserSessionManager(context)

    fun getBalasan(callback: () -> Any){
        viewModelScope.launch {
            try {
                val result = HttpApi.retrofitService.getBalasan(
                    userSessionManager.getLoginInfo().getValue("id").toString(),
                    userSessionManager.getToken().toString()
                )
                if(result.isSuccessful){
                    _alamatRes.value = BalasanResult(success = result.body())
                    Log.d("sok", result.body().toString())
                    _memberList.value = result.body()!!.data
                }
                else{
                    Log.d("sok", result.errorBody()!!.string())
                    val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                    _alamatRes.value = BalasanResult(error = responseJson.message)
                }
                callback()
            } catch (e: Exception){
                _alamatRes.value = BalasanResult(error = "Request Error")
            }
        }
    }

    fun init(){
        _memberList.value = listOf<BalasanItem>()
    }
}

data class BalasanItem(
    val id : String,
    val title: String,
    val body: String,
    val name: String,
    val email: String
)