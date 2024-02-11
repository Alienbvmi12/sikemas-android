package com.example.sikemasapp

import android.Manifest
import android.app.Service
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.RemoteMessage.Notification

class MyFirebaseInstanceIDService : FirebaseMessagingService(){
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.e("Pesan diterima", message.notification?.title.toString())
        Toast.makeText(this, message.notification?.title.toString(), Toast.LENGTH_SHORT).show()
        summonNotification(message.notification?.title.toString(), message.notification?.body.toString())

    }

    private fun summonNotification(title: String, body: String){
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, "FirebaseChannel")
            .setSmallIcon(R.drawable.alarm_1)
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)

        val manager: NotificationManagerCompat = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        manager.notify(1001, builder.build())
    }

}