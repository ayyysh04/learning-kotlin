package com.example.myapplication.androidServices

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.androidServices.demoStopwatch.DemoStopwatchActivity
import com.example.myapplication.databinding.ActivityAndroidServiceBinding

class AndroidServiceActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAndroidServiceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAndroidServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goToDemoStopWatch.setOnClickListener {
            val intent: Intent = Intent(this, DemoStopwatchActivity::class.java)
            startActivity(intent)
        }
        //creating INTENT to start the service
        val serviceIntent = Intent(this, MyBackgroundService::class.java)
//        sending extra info to service
        serviceIntent.putExtra(MyBackgroundService.NAME, "Alex")
        serviceIntent.putExtra(MyBackgroundService.MARKS, 78)

        binding.btnStart.setOnClickListener {
            Log.i(MyBackgroundService.TAG, "Starting service")
            startService(serviceIntent)
        }

        binding.btnStop.setOnClickListener {
            Log.i(MyBackgroundService.TAG, "Stopping service")
            stopService(serviceIntent)
        }
    }
}