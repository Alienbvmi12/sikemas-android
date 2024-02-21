package com.example.sikemasapp

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.Settings
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class MyFirebaseInstanceIDService : FirebaseMessagingService(){
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.e("Pesan diterima", message.notification?.title.toString())
        pushNotification(message.notification?.title.toString(), message.notification?.body.toString())
    }

    private fun pushNotification(title: String, body: String){
        createNotificationChannel()

        //Create intent
        val intent = Intent(this, LoadingScreenActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        //retrieve layout
        val customLayout = RemoteViews(packageName, R.layout.fragment_notification_layout)
        customLayout.setTextViewText(R.id.notification_title, title)
        customLayout.setTextViewText(R.id.notification_text, body)

        //Build Notification
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(this, "FirebaseChannel")
            .setSmallIcon(R.drawable.alarm_1)
            .setContent(customLayout)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setColor(ContextCompat.getColor(this, R.color.warning))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        //push notification
        val manager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(!checkNotificationPermission()){
            requestNotificationPermission()
        }
        else{
            manager.notify(1123, builder.build())
            val v = getSystemService(VIBRATOR_SERVICE) as Vibrator
            // Vibrate for 500 milliseconds
            // Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                v.vibrate(VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                //deprecated in API 26
                v.vibrate(500)
            }
        }
    }

    private fun createNotificationChannel(id: String = "FirebaseChannel"){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel.
            val name = "Test"
            val descriptionText = "Test Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(id, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    private fun checkNotificationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
            startActivity(intent)
        } else {
            val intent = Intent("android.settings.APP_NOTIFICATION_SETTINGS")
                .putExtra("app_package", packageName)
                .putExtra("app_uid", applicationInfo.uid)
            startActivity(intent)
        }
    }

}