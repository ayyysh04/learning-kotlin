package com.example.myapplication.viewBinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityViewBindingBinding

class ViewBindingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewBindingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewBindingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        INSTEAD OF WRITING VINDING EVERYTIME , WE CAN USE THS BINDING.APPLY FUNCTION TO AVOID IT
        binding.apply {
            btnSubmit.setOnClickListener {
                val name = etName.text.toString()
                tvMessage.text = "Hello! $name"
                etName.setText("")
            }
        }
    }
}