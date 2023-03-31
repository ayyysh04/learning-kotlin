package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("MYTAG", "SecondActivity")//used for debugginh
//        rightclick -> generate to get and generate the overriden method of parent class
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val userName = intent.getStringExtra("USER")
        val textView = findViewById<TextView>(R.id.tvOffer)

        val message = "$userName ,you will get  free access to all the content for one month "
        textView.text = message

    }
}