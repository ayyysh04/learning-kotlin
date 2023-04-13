package com.example.myapplication.navigationComponents.dataBinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityNavDatabindingBinding

class NavDataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavDatabindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_nav_databinding)

    }
}
