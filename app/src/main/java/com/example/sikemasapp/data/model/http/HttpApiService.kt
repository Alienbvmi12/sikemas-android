package com.example.sikemasapp.data.model.http

import android.content.SharedPreferences
import com.example.sikemasapp.LoadingScreenActivity
import com.example.sikemasapp.MainActivity2
import com.example.sikemasapp.data.storage.ApiSessionManager
import com.example.sikemasapp.data.viewModel.alamat.AlamatItem
import com.example.sikemasapp.data.viewModel.balasan.BalasanItem
import com.google.android.gms.common.internal.safeparcel.SafeParcelable
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

const val BASE_URL = "https://guided-cow-diverse.ngrok-free.app/sikemas-api-seme/"
private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
private val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL).build()
interface HttpApiService{

    //Testing
    @POST("api/auth/login/")
    suspend fun login(@Body body: Map<String, String>): Response<HttpResponse>

    @POST("api/auth/logout/")
    suspend fun logout(@Body body: Map<String, String>): Response<HttpResponse>

    @POST("api/auth/register/")
    suspend fun register(@Body body: Map<String, String>): Response<HttpResponse>

    @POST("api/auth/resend_otp/")
    suspend fun resendOtp(@Body body: Map<String, String>): Response<HttpResponse>

    @POST("api/auth/verify_email/")
    suspend fun verifyEmail(@Body body: Map<String, String>): Response<HttpResponse>

    @POST("api/auth/request_reset_password_otp/")
    suspend fun requestResetPassword(@Body ody: Map<String, String>): Response<HttpResponse>

    @POST("api/auth/reset_password_otp_verify/")
    suspend fun resetPasswordOtpVerify(@Body body: Map<String, String>): Response<HttpResponse>

    @POST("api/auth/reset_password/")
    suspend fun resetPassword(@Body body: Map<String, String>): Response<HttpResponse>

    @GET("api/user/get/{user_id}/")
    suspend fun getProfile(
        @Path("user_id") id: String,
        @Header("Authorization") token: String
    ): Response<HttpResponse>

    @GET("api/ronda/get_jadwal/{day}/")
    suspend fun getJadwal(
        @Path("day") day: String,
        @Header("Authorization") token: String
    ): Response<HttpResponseList>

    @GET("api/alamat/search/{keyword}")
    suspend fun getAlamat(
        @Path("keyword") keyword: String,
        @Header("Authorization") token: String
    ): Response<HttpResponseAlamat>

    @GET("api/balasan/get/{id}")
    suspend fun getBalasan(
        @Path("id") keyword: String,
        @Header("Authorization") token: String
    ): Response<HttpResponseBalasan>

    @POST("api/darurat/emergency_alarm/")
    suspend fun triggerAlarm(
        @Header("Authorization") token: String,
        @Body body: Map<String, String>
    ): Response<HttpResponse>

    @POST("api/aspirasi/post_aspirasi/")
    suspend fun sendAspirasi(
        @Header("Authorization") token: String,
        @Body body: Map<String, String>
    ): Response<HttpResponse>
}

object HttpApi{
    val retrofitService: HttpApiService by lazy{
        retrofit.create(HttpApiService::class.java)
    }
}

data class HttpResponse(
    val status: Int,
    val message: String,
    val data: Map<String, Any>
)

data class HttpResponseList(
    val status: Int,
    val message: String,
    val data: List<Map<String, Any>?>
)

data class HttpResponseAlamat(
    val status: Int,
    val message: String,
    val data: List<AlamatItem>
)

data class HttpResponseBalasan(
    val status: Int,
    val message: String,
    val data: List<BalasanItem>
)