package com.example.sikemasapp.data.viewModel.profile

import android.content.Context
import android.provider.ContactsContract.Profile
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

class ProfileViewModel(
    private val context: Context
) : ViewModel() {
    private val _profileInfo = MutableLiveData<ProfileInfo>()
    val profileInfo: LiveData<ProfileInfo> = _profileInfo

    private val userSessionManager: UserSessionManager = UserSessionManager(context)

    fun logout(){
        userSessionManager.clearSession()
    }

    fun getProfile():ProfileInfo? {
        return if (userSessionManager.isLoginInfoExist()){
            val user = userSessionManager.getLoginInfo()
            val userId = user.getValue("id").toString()
            val username = user.getValue("username").toString()

            _profileInfo.value = ProfileInfo(
                userId,
                username,
                username,
                username
            )

            return profileInfo.value
        } else{
            return null
        }
    }
}