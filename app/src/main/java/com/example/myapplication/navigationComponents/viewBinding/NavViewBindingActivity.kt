package com.example.myapplication.navigationComponents.viewBinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityNavViewbindingBinding

class NavViewBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavViewbindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavViewbindingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}