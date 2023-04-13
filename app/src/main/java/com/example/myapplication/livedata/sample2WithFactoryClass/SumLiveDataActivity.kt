package com.example.myapplication.livedata.sample2WithFactoryClass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySumLiveDataBinding

class SumLiveDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySumLiveDataBinding
    private lateinit var viewModel: SumViewModel
    private lateinit var viewModelFactory: SumFactoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sum_live_data)
        viewModelFactory = SumFactoryViewModel(125)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SumViewModel::class.java)

        viewModel.totalData.observe(this, Observer {
            binding.resultTextView.text = it.toString()
        })

        binding.insertButton.setOnClickListener {
            viewModel.setTotal(binding.inputEditText.text.toString().toInt())
        }
    }
}