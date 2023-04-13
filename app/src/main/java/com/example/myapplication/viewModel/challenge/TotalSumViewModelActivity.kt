package com.example.myapplication.viewModel.challenge

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityTotalSumViewModelBinding
import com.example.myapplication.livedata.sample2WithFactoryClass.SumLiveDataActivity

class TotalSumViewModelActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTotalSumViewModelBinding
    private lateinit var viewModel: SumViewModel
    private lateinit var viewModelFactoryClass: SumViewModelFactoryClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_total_sum_view_model)
        viewModelFactoryClass = SumViewModelFactoryClass(125)
        viewModel = ViewModelProvider(this, viewModelFactoryClass).get(SumViewModel::class.java)
        binding.resultTextView.text = viewModel.getTotal().toString()

        binding.insertButton.setOnClickListener {
            viewModel.setTotal(binding.inputEditText.text.toString().toInt())
            binding.resultTextView.text = viewModel.getTotal().toString()

        }
        binding.goToLiveSumView.setOnClickListener {
            val intent = Intent(this, SumLiveDataActivity::class.java)
            startActivity(intent)
        }
    }
}