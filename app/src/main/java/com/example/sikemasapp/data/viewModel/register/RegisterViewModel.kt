package com.example.sikemasapp.data.viewModel.register

import android.content.Context
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
import com.example.sikemasapp.data.storage.RegisterSessionManager
import com.example.sikemasapp.data.storage.UserSessionManager
import com.example.sikemasapp.ui.view.register.LoggedInUserView
import com.example.sikemasapp.ui.view.login.LoginFormState
import com.example.sikemasapp.ui.view.login.LoginResult
import com.example.sikemasapp.ui.view.register.RegisterResult
import com.google.gson.Gson
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val loginRepository: LoginRepository,
    private val context: Context
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    private val _resendResult = MutableLiveData<RegisterResult>()
    val resendResult: LiveData<RegisterResult> = _resendResult

    private val registerSessionManager: RegisterSessionManager = RegisterSessionManager(context)
    private val userSessionManager: UserSessionManager = UserSessionManager(context)

    fun register(nik: String, email: String, username: String, password: String, confirmPassword: String) {
        viewModelScope.launch {
            // can be launched in a separate asynchronous job
            val result = HttpApi.retrofitService.register(mapOf(
                "nik" to nik,
                "email" to email,
                "username" to username,
                "password" to password,
                "confirm_password" to confirmPassword
            ))

            if (result.isSuccessful) {
                _registerResult.value =
                    RegisterResult(success = LoggedInUserView(displayName = result.body()!!.data["username"].toString()))
                registerSessionManager.saveRegisterInfo(
                    result.body()!!.data["reg_id"].toString(),
                    result.body()!!.data["username"].toString(),
                    result.body()!!.data["email"].toString()
                )
            } else {
                val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                _registerResult.value = RegisterResult(error = responseJson.message.toString())
            }
        }
    }

    fun submitOtp(otp: String){
        viewModelScope.launch {
            val result = HttpApi.retrofitService.verifyEmail(mapOf(
                "reg_id" to registerSessionManager.getRegisterInfo().getValue("reg_id").toString(),
                "otp" to otp
            ))

            if (result.isSuccessful) {
                _registerResult.value =
                    RegisterResult(success = LoggedInUserView(displayName = result.body()!!.message))
                userSessionManager.saveLoginInfo(
                    result.body()!!.data["id"].toString(),
                    result.body()!!.data["username"].toString(),
                    result.body()!!.data["email"].toString()
                )
                userSessionManager.saveToken(result.body()!!.data["token"].toString())
                registerSessionManager.clearSession()
            } else {
                val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                _registerResult.value = RegisterResult(error = responseJson.message.toString())
            }
        }
    }

    fun resendOtp(){
        viewModelScope.launch {
            val registerInfo = registerSessionManager.getRegisterInfo()
            val result = HttpApi.retrofitService.resendOtp(mapOf(
                "reg_id" to registerInfo.getValue("reg_id").toString(),
                "email" to registerInfo.getValue("email").toString(),
                "username" to registerInfo.getValue("username").toString()
            ))
            if (result.isSuccessful) {
                _resendResult.value =
                    RegisterResult(success = LoggedInUserView(displayName = result.body()!!.message))
            } else {
                val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                _resendResult.value = RegisterResult(error = responseJson.message.toString())
            }
        }
    }
}