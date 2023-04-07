package com.example.myapplication.notificationComponents


import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.example.myapplication.databinding.ActivityNotificationDemoBinding

//Notifications come under behaviour component of android jetpack
//notification is message that comes outside app UI
//used for providing reminders ,to communicate with the users ,or to provide timely information
//user can open the app or sometimes take app related action directly from notification by tapping on it
//when our app issues a notification ,it appears as a icon in the status bar
//From android 5.0 ,notification can briefly appear in floating window called as heads-up notification -> provide information that user should know immediately
class NotificationDemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNotificationDemoBinding
    private val channelID = "com.example.myapplication.channel1"
    private val channelName = "DemoChannel"
    private val channelDescription = "this is a demo"

    private var notificationManager: NotificationManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationDemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            displayNotification()
        }

        //initializing notificationManager
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        Creating a notification channel involves registering it with the NotificationManager system service. You only need to create the notification channel once, typically in the onCreate() method of your Activity or Application class. Once the notification channel is created, you can reuse it to show notifications throughout your app.
//If you don't create a notification channel, the notification will not be displayed on Android 8.0 and higher, and your app will throw a NullPointerException when trying to show the notification.
        createNotificationChannel(channelID, channelName, channelDescription)
    }

    private fun displayNotification() {
        val notificationId =
            45 //a unique integer value that identifies a notification. You use this ID to update or cancel a notification later on
        val notification = NotificationCompat.Builder(this@NotificationDemoActivity, channelID)
            .setContentTitle("Demo Title")
            .setContentText("This is a demo notification")
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()
        notificationManager?.notify(notificationId, notification)

    }


    private fun createNotificationChannel(id: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance =
                NotificationManager.IMPORTANCE_HIGH //determines how to interrupt the user for any notification that belongs to this channel
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }
            notificationManager?.createNotificationChannel(channel)

        }

    }
}