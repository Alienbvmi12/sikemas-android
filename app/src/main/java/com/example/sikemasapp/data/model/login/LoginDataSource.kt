package com.example.sikemasapp.data.model.login

import android.util.Log
import com.example.sikemasapp.data.model.http.HttpApi
import com.example.sikemasapp.data.model.http.HttpResponse
import com.example.sikemasapp.data.storage.UserSessionManager
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import java.io.IOException
import java.util.UUID

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    private val http = HttpApi.retrofitService
    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        try {
            var result: Response<HttpResponse> = http.login(mapOf(
                "username" to username,
                "password" to password
            ))
            Log.e("fee", result.body()!!.toString())
            // TODO: handle loggedInUser authentication
            if(result.isSuccessful){
                val userData = result.body()!!
                val user = LoggedInUser(
                    userData.data["id"].toString(),
                    userData.data["username"].toString(),
                    userData.data["email"].toString(),
                    userData.data["token"].toString()
                )
                return Result.Success(user)
            }
            else{
                return Result.Error(IOException("Username atau password salah"))
            }
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}