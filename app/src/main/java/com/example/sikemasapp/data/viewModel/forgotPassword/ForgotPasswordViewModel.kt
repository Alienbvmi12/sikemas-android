package com.example.sikemasapp.data.viewModel.forgotPassword

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
import com.example.sikemasapp.data.storage.ForgotSessionManager
import com.example.sikemasapp.data.storage.RegisterSessionManager
import com.example.sikemasapp.data.storage.UserSessionManager
import com.example.sikemasapp.ui.view.register.LoggedInUserView
import com.example.sikemasapp.ui.view.login.LoginFormState
import com.example.sikemasapp.ui.view.login.LoginResult
import com.example.sikemasapp.ui.view.register.RegisterResult
import com.google.gson.Gson
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(
    private val loginRepository: LoginRepository,
    private val context: Context
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _otpReqResult = MutableLiveData<RegisterResult>()
    val otpReqResult: LiveData<RegisterResult> = _otpReqResult

    private val _otpReqResult2 = MutableLiveData<RegisterResult>()
    val otpReqResult2: LiveData<RegisterResult> = _otpReqResult2

    private val forgotSessionManager: ForgotSessionManager = ForgotSessionManager(context)

    fun submitEmail(email: String) {
        viewModelScope.launch {
            // can be launched in a separate asynchronous job
            try{
                val result = HttpApi.retrofitService.requestResetPassword(mapOf(
                    "email" to email
                ))

                if (result.isSuccessful) {
                    _otpReqResult.value =
                        RegisterResult(success = LoggedInUserView(displayName = result.body()!!.message.toString()))
                    forgotSessionManager.saveEmail(email)
                } else {
                    val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                    _otpReqResult.value = RegisterResult(error = responseJson.message)
                }
            } catch (e:Exception){
                _otpReqResult.value = RegisterResult(error = "Request Error")
            }
        }
    }

    fun resendOtp() {
        viewModelScope.launch {
            try{
                // can be launched in a separate asynchronous job
                val email = forgotSessionManager.getEmail().toString()
                val result = HttpApi.retrofitService.requestResetPassword(mapOf(
                    "email" to email
                ))

                if (result.isSuccessful) {
                    _otpReqResult2.value =
                        RegisterResult(success = LoggedInUserView(displayName = "Berhasil kirim ulang kode!!"))
                } else {
                    val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                    _otpReqResult2.value = RegisterResult(error = responseJson.message)
                }
            } catch (e:Exception){
                _otpReqResult2.value = RegisterResult(error = "Request Error")
            }
        }
    }

    fun submitOtp(otp: String) {
        viewModelScope.launch {
            try{
                // can be launched in a separate asynchronous job
                val email = forgotSessionManager.getEmail().toString()
                val result = HttpApi.retrofitService.resetPasswordOtpVerify(mapOf(
                    "email" to email,
                    "otp" to otp
                ))

                if (result.isSuccessful) {
                    _otpReqResult.value =
                        RegisterResult(success = LoggedInUserView(displayName = result.body()!!.message))
                    forgotSessionManager.saveOtp(otp)
                } else {
                    val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                    _otpReqResult.value = RegisterResult(error = responseJson.message)
                }
            } catch (e:Exception){
                _otpReqResult.value = RegisterResult(error = "Request Error")
            }
        }
    }

    fun submitPassword(password: String, confirmPassword: String) {
        viewModelScope.launch {
            try{
                // can be launched in a separate asynchronous job
                val email = forgotSessionManager.getEmail().toString()
                val otp = forgotSessionManager.getOtp().toString()
                val result = HttpApi.retrofitService.resetPassword(mapOf(
                    "email" to email,
                    "otp" to otp,
                    "password" to password,
                    "confirm_password" to confirmPassword
                ))

                if (result.isSuccessful) {
                    _otpReqResult.value =
                        RegisterResult(success = LoggedInUserView(displayName = result.body()!!.message))
                    forgotSessionManager.clearSession()
                } else {
                    val responseJson: HttpResponse = Gson().fromJson(result.errorBody()!!.string(), HttpResponse::class.java)
                    _otpReqResult.value = RegisterResult(error = responseJson.message)
                }
            } catch (e:Exception){
                _otpReqResult.value = RegisterResult(error = "Request Error")
            }
        }
    }
}