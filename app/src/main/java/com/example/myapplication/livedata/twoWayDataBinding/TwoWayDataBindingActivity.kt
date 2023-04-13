package com.example.myapplication.livedata.twoWayDataBinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityTwoWayDataBindingBinding

class TwoWayDataBindingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTwoWayDataBindingBinding
    private lateinit var viewModel: TwoWayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_data_binding)
        viewModel = ViewModelProvider(this).get(TwoWayViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

    }

}
