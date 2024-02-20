package com.example.sikemasapp.data.viewModel.login

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.sikemasapp.R
import com.example.sikemasapp.data.model.http.BASE_URL
import com.example.sikemasapp.data.model.http.HttpApi
import com.example.sikemasapp.data.model.http.HttpApiService
import com.example.sikemasapp.data.model.http.moshi
import com.example.sikemasapp.data.model.http.retrofit
import com.example.sikemasapp.data.model.login.LoggedInUser
import com.example.sikemasapp.data.model.login.LoginRepository
import com.example.sikemasapp.data.model.login.Result
import com.example.sikemasapp.data.storage.ApiSessionManager
import com.example.sikemasapp.data.storage.UserSessionManager
import com.example.sikemasapp.ui.view.login.LoggedInUserView
import com.example.sikemasapp.ui.view.login.LoginFormState
import com.example.sikemasapp.ui.view.login.LoginResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class LoginViewModel(
    private val loginRepository: LoginRepository,
    private val context: Context
) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val userSessionManager: UserSessionManager = UserSessionManager(context)

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try{
                // can be launched in a separate asynchronous job
                val result = HttpApi.retrofitService.login(mapOf(
                    "username" to username,
                    "password" to password
                ))

                if (result.isSuccessful) {
                    _loginResult.value =
                        LoginResult(success = LoggedInUserView(displayName = result.body()!!.data["username"].toString()))
                    userSessionManager.saveLoginInfo(
                        result.body()!!.data["id"].toString(),
                        result.body()!!.data["username"].toString(),
                        result.body()!!.data["email"].toString(),
                        result.body()!!.data["profile"].toString()
                    )
                    userSessionManager.saveToken(result.body()!!.data["token"].toString())
                } else {
                    _loginResult.value = LoginResult(error = R.string.login_failed)
                }
            } catch (e:Exception){
                _loginResult.value = LoginResult(error = R.string.login_failed)
            }
        }
    }

    fun isLoggedIn(): Boolean {
        return if (userSessionManager.isLoginInfoExist()){
            val user = userSessionManager.getLoginInfo()
            val userId = user.getValue("id").toString()
            val username = user.getValue("username").toString()
            val email = user.getValue("email").toString()
            val token = userSessionManager.getToken().toString()

            loginRepository.setLoggedInUser(LoggedInUser(userId, username, email, token))
            _loginResult.value = LoginResult(success = LoggedInUserView(displayName = username))

            true
        } else{
            false
        }
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }
}