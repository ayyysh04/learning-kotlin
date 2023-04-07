package com.example.myapplication.androidServices

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class MyBackgroundService : Service() {

    init {
        Log.i(TAG, "Service has been created")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "Service Started")
//        getting extra data from activity or fragment called
        val name = intent?.getStringExtra(NAME)
        val marks = intent?.getIntExtra(MARKS, 0)
        Log.i(TAG, "name is $name , marks : $marks")

//        Sometimes android may have to destroy and restart the running service in order to manage its resources among the most
//        prioritized apps ,this return value is used by android system to decide how the system restart the service after being destroyed by it if we choose return ,start
//        or redeliver intent -> system will pass the last intent to the service when restarting
//        START_NOTSTICKY -> system will not restart service automatically
        return START_STICKY
    }

    //
    override fun onBind(p0: Intent?): IBinder? = null

    override fun onDestroy() {
        Log.i(TAG, "Destroying....")
        super.onDestroy()
    }

    //    we can use companion objects to avoid using same string or code again and again
//    this object acts as companion to other objects just like static in java
//    companion objects are singleton objects whose properties and functions are tied to a class  not to the instance of the class
    companion object {
        const val TAG = "MYTAG"
        const val NAME = "NAME"
        const val MARKS = "TOTAL MARKS"
    }


}