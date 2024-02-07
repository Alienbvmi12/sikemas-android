package com.example.sikemasapp.data.viewModel.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.example.sikemasapp.R
import com.example.sikemasapp.data.model.login.LoggedInUser
import com.example.sikemasapp.data.model.login.LoginRepository
import com.example.sikemasapp.data.model.login.Result
import com.example.sikemasapp.data.storage.UserSessionManager
import com.example.sikemasapp.ui.view.login.LoggedInUserView
import com.example.sikemasapp.ui.view.login.LoginFormState
import com.example.sikemasapp.ui.view.login.LoginResult

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
        // can be launched in a separate asynchronous job
        val result = loginRepository.login(username, password)

        if (result is Result.Success) {
            _loginResult.value =
                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
            userSessionManager.saveLoginInfo(result.data.userId, result.data.displayName)
            userSessionManager.saveToken("fwuie213e3e32")
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
    }

    fun logout(){
        userSessionManager.clearSession()
    }

    fun isLoggedIn(): Boolean {
        return if (userSessionManager.isLoginInfoExist()){
            val user = userSessionManager.getLoginInfo()
            val userId = user.getValue("id").toString()
            val username = user.getValue("username").toString()

            loginRepository.setLoggedInUser(LoggedInUser(userId, username))
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