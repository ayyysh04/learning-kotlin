package com.example.myapplication.dataBinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityDataBindingWithObjectBinding

class DataBindingWithObjectActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBindingWithObjectBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //general way of setting the object to view
//        val stuObject: Student = getStudent()
//        binding.nameText.text = stuObject.name
//        binding.emailText.text = stuObject.email

        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding_with_object)
        //using data object we can directly set the data in view
        binding.student = getStudent()
    }

    private fun getStudent(): Student {
        //this can be api or database where we are fetching the object
        return Student(1, "Alex", "alex@gmail.com")
    }
}