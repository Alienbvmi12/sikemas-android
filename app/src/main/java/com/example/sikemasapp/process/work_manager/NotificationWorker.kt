package com.example.sikemasapp.process.work_manager

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.sikemasapp.MyFirebaseInstanceIDService

class NotificationWorker(
    val context: Context,
    params: WorkerParameters
) : Worker(context, params) {
    override fun doWork(): Result {
        context.startService(Intent(context, MyFirebaseInstanceIDService::class.java))
        return Result.success()
    }

}