package com.example.sikemasapp.data.viewModel.profile

import android.content.Context
import android.provider.ContactsContract.Profile
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.sikemasapp.R
import com.example.sikemasapp.data.model.http.HttpApi
import com.example.sikemasapp.data.model.http.HttpResponse
import com.example.sikemasapp.data.model.login.LoggedInUser
import com.example.sikemasapp.data.model.login.LoginRepository
import com.example.sikemasapp.data.model.login.Result
import com.example.sikemasapp.data.storage.UserSessionManager
import com.example.sikemasapp.ui.view.login.LoggedInUserView
import com.example.sikemasapp.ui.view.login.LoginFormState
import com.example.sikemasapp.ui.view.login.LoginResult
import com.example.sikemasapp.ui.view.profile.ProfileResult
import com.example.sikemasapp.ui.view.register.RegisterResult
import com.google.gson.Gson
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val context: Context
) : ViewModel() {
    private val _userData = MutableLiveData<ProfileResult>()
    val userData: LiveData<ProfileResult> = _userData

    private val _displayProfile = MutableLiveData<List<Map<String, Any>>>()
    val displayProfile: LiveData<List<Map<String, Any>>> = _displayProfile

    private val userSessionManager: UserSessionManager = UserSessionManager(context)

    fun logout(callback: () -> Any){
        viewModelScope.launch {
            HttpApi.retrofitService.logout(
                mapOf(
                    "token" to userSessionManager.getToken().toString()
                )
            )
            userSessionManager.clearSession()
            callback()
        }
    }

    fun getProfile(id: String = userSessionManager.getLoginInfo().getValue("id").toString()){
        viewModelScope.launch {
            if (userSessionManager.isLoginInfoExist()){
                val result = HttpApi.retrofitService.getProfile(
                    id,
                    userSessionManager.getToken().toString()
                )
                if(result.isSuccessful) {
                    _userData.value = ProfileResult(success = result.body()!!)
                    setDisplayProfile(result.body()!!)
                }
                else{
                    val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                    _userData.value = ProfileResult(error = responseJson.message)
                }
            } else{
                _userData.value = ProfileResult(error = "belum login")
            }
        }
    }

    fun setDisplayProfile(response: HttpResponse){
        val userData: MutableMap<String, Any> = response.data.toMutableMap()
        userData["alamat"] = userData.getValue("no_rumah").toString() + ", " +
                "RT." + userData.getValue("rt").toString() + "/" +
                "RW." + userData.getValue("rw").toString() + ", " +
                userData.getValue("kelurahan").toString() + ", " +
                "Kecamatan " + userData.getValue("kecamatan").toString() + ", " +
                userData.getValue("kota").toString() + ", " +
                userData.getValue("provinsi").toString() + ". " +
                userData.getValue("kode_pos").toString() + " "

        userData.remove("no_rumah")
        userData.remove("rt")
        userData.remove("rw")
        userData.remove("kelurahan")
        userData.remove("kecamatan")
        userData.remove("kota")
        userData.remove("provinsi")
        userData.remove("kode_pos")
        userData.remove("foto")
        userData.remove("id")
        userData.remove("warga_id")
        userData.remove("alamat_id")
        userData.remove("email")
        userData.remove("username")

        val userDataList = mutableListOf<Map<String, Any>>()

        userData.forEach{ (key, value) ->
            userDataList.add(mapOf(key.replace("_", " ") to if(value === null) "-" else value.toString() ))
        }

        _displayProfile.value = userDataList
    }
}