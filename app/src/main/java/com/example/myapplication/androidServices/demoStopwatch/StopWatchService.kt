package com.example.myapplication.androidServices.demoStopwatch

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.util.*

//serive that used intent and broadcast to make communication with the activity and also as we know Services runs in background and foreground both
class StopWatchService : Service() {
    override fun onBind(p0: Intent?): IBinder? = null
    private val timer = Timer()

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val time = intent.getDoubleExtra(CURRENT_TIME, 0.0)

        //schedules a task of type StopWatchTimerTask to be executed repeatedly at a fixed rate of 1000 milliseconds (or 1 second), using a Timer instance timer.
        timer.scheduleAtFixedRate(StopWatchTimerTask(time), 0, 1000)
        return START_NOT_STICKY//system will not restart service automatically
    }

    override fun onDestroy() {
        timer.cancel()
        super.onDestroy()
    }

    companion object {
        const val CURRENT_TIME =
            "current time" //CURRENT_TIME is a constant string that is used as the key to put the current time value in the Intent object that is sent by the StopWatchService to the MainActivity.
        const val UPDATED_TIME =
            "updated time"//UPDATED_TIME is another constant string that is used as the name of the broadcast Intent that is sent by the StopWatchTimerTask class to notify the MainActivity that the time has been updated.
    }

    //inner keyword make class as inner class allowing the StopWatchService members like send broadcast to be accessible
//    extends TimerTask, which is a class that represents a task that can be scheduled to run at a specified time or repeatedly at a fixed rate.
    private inner class StopWatchTimerTask(private var time: Double) : TimerTask() {
        override fun run() {
            val intent = Intent(UPDATED_TIME)
            time++
            intent.putExtra(CURRENT_TIME, time)
            //new Intent object is created with the action UPDATED_TIME. This intent is used to send a broadcast to inform the UI that the time has been updated.
            //The value of time is then incremented by one to reflect the elapsed time for the stopwatch, and this updated value is added to the Intent object using putExtra() method, along with the key CURRENT_TIME.
            //sending broadcast
            sendBroadcast(intent)
        }

    }
}