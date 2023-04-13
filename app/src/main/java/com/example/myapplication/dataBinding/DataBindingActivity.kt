package com.example.myapplication.dataBinding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.dataBinding.challenge.DataBindingChallengeActivity
import com.example.myapplication.databinding.ActivityDataBindingBinding

class DataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding)
        binding.submitButton.setOnClickListener {
            displayGreeting()
        }

        binding.goToChallengeDataBinding.setOnClickListener {
            val intent = Intent(this, DataBindingChallengeActivity::class.java)
            startActivity(intent)
        }

        binding
            .goToDataBindingWithObjects.setOnClickListener {
                val intent = Intent(this, DataBindingWithObjectActivity::class.java)
                startActivity(intent)
            }
    }

    private fun displayGreeting() {
        binding.apply {
            greetingTextView.text = "Hello! " + nameEditText.text
        }

    }
}