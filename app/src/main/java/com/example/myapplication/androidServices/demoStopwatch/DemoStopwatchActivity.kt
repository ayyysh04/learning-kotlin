package com.example.myapplication.androidServices.demoStopwatch

import android.app.ActivityManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityDemoStopwatchBinding
import kotlin.math.roundToInt

class DemoStopwatchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDemoStopwatchBinding
    private var isStarted = false
    private lateinit var serviceIntent: Intent
    private var time = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoStopwatchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            startOrStop()
        }
        binding.btnReset.setOnClickListener {
            reset()
        }

        //used application context as the service will run in background or foreground both
//        stores the Service Intent
        serviceIntent = Intent(applicationContext, StopWatchService::class.java)

        //need to register the receiver
//        read about IntentFilter in detail - IntentFilter info.txt
        registerReceiver(updateTime, IntentFilter(StopWatchService.UPDATED_TIME))
// This line of code registers a broadcast receiver updateTime to listen for broadcasts with the action StopWatchService.UPDATED_TIME.
//The registerReceiver() method takes two parameters: the first is the BroadcastReceiver object to register and the second is an IntentFilter object that specifies the action that the receiver is interested in listening for.
//In this case, the BroadcastReceiver object is updateTime, which is an inner class of the MainActivity class that implements the BroadcastReceiver interface. The IntentFilter object specifies the action that the updateTime receiver is interested in listening for, which is StopWatchService.UPDATED_TIME.
//When a broadcast with the action StopWatchService.UPDATED_TIME is sent from the StopWatchService, the updateTime receiver will receive the broadcast and its onReceive() method will be called. In this method, the time variable is updated with the new time value sent by the service and the UI of the MainActivity is updated to display the updated time value.

        val runningService = isServiceRunning(StopWatchService::class.java)
        isStarted = runningService
        binding.btnStart.text = if (runningService) "Stop" else "Start"
    }

    private fun startOrStop() {
        if (isStarted)
            stop()
        else
            start()
    }

    private fun start() {
        serviceIntent.putExtra(StopWatchService.CURRENT_TIME, time)
        startService(serviceIntent) //starts the service
        binding.btnStart.text = "Stop"
        isStarted = true
    }

    private fun stop() {
        stopService(serviceIntent)
        binding.btnStart.text = "Start"
        isStarted = false
    }

    private fun reset() {
        stop()
        time = 0.0
        binding.tvTime.text = getFormattedTime(time)

    }

    //    BroadcastReceiver are the android components which allow us to send or receive events
//    The updateTime variable is a BroadcastReceiver that receives the current time from the StopWatchService and updates the time variable and the text view.
//    object is used to directly crate object of class implementing the BroadcastReceiver
    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            time = intent.getDoubleExtra(StopWatchService.CURRENT_TIME, 0.0)
            binding.tvTime.text = getFormattedTime(time)
        }
    }

    private fun getFormattedTime(time: Double): String {
//        converted using division and remainder operator
        val timeInt = time.roundToInt()
//        timeInt % 86400 -> remaining no of sec after 24 hrs
        val hours =
            timeInt % 86400 / 3600 // after 24hr i.e 1 day hrs will set to 0 using remainder function
//        calculates the number of hours by taking the remainder of timeInt when divided by the number of seconds in a day (86400) and then dividing by the number of seconds in an hour (3600).
//       timeInt % 86400 % 3600 -> remaining no of sec after 60 min
        val minutes =
            timeInt % 86400 % 3600 / 60 //calculates the number of minutes by taking the remainder of timeInt when divided by the number of seconds in a day and then the remainder of that value when divided by the number of seconds in an hour, and then dividing by the number of seconds in a minute (60).
//       timeInt % 86400 % 3600 % 60 -> remaining no of sec after 60 sec
        val seconds =
            timeInt % 86400 % 3600 % 60 //  Finally, the expression timeInt % 86400 % 3600 % 60 calculates the number of seconds by taking the remainder of timeInt when divided by the number of seconds in a day, then the remainder of that value when divided by the number of seconds in an hour, and then the remainder of that value when divided by the number of seconds in a minute.

        return String.format("%02d:%02d:%02d", hours, minutes, seconds)
//        The %02d:%02d:%02d format string specifies that the formatted string should include three integer values, separated by colons, with each value taking up two characters (or digits), with leading zeros added as necessary.
//        For example, if hours = 2, minutes = 5, and seconds = 38, the formatted string returned by this statement would be "02:05:38".
    }

    // Function to check if a service is running or not
//    out keyword allow class that implements Service class to passed as argument -> read more about from notes
    private fun isServiceRunning(serviceClass: Class<out Service>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        @Suppress("DEPRECATION")
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }


}



