package com.example.myapplication.livedata

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

//Live Data : Lifecycle aware observable data holder class ,and used to get Live Data i.e real time data
//LiveData is a class in the Android Architecture Components library that is used for building data objects that notify views when the underlying data changes. It's an observable data holder class that is lifecycle-aware, which means that it respects the lifecycle of other app components, such as activities and fragments.
//The main purpose of LiveData is to provide a way to update the UI in response to data changes without requiring the developer to manage complex lifecycle-related code. It ensures that the UI only updates when it's in the foreground, and it automatically cleans up resources when the UI is no longer visible.
//The LiveData class provides several benefits over traditional observable data models. It automatically handles lifecycle events, such as configuration changes, and it ensures that the observer always has the latest data. It also simplifies communication between different components of the app and helps to decouple the UI from the data layer.
//works like provider in flutter ,informing the view when the data is changed and update it accordingly
class LiveDataActivity : AppCompatActivity() {
    var count = 0
    private lateinit var viewModel: LiveDataActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("EXAMPLE_APP", "onCreate ViewModelDemo")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        val textView = findViewById<TextView>(R.id.tvCount)
        val button = findViewById<Button>(R.id.btnCount)
        viewModel = ViewModelProvider(this).get(LiveDataActivityViewModel::class.java)
        viewModel.count.observe(this, Observer {
            textView.text = it.toString()
        })

        button.setOnClickListener {
            viewModel.updateCount() // automatically update the text View Data using above observe function
        }
    }

}