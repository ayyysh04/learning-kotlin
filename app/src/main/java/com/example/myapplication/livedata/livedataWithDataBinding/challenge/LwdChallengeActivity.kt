package com.example.myapplication.livedata.livedataWithDataBinding.challenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLwdChallengeBinding

class LwdChallengeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLwdChallengeBinding
    private lateinit var viewModel: LwdChallengeViewModel
    private lateinit var viewModelFactory: LwdViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lwd_challenge)
        viewModelFactory = LwdViewModelFactory(125)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LwdChallengeViewModel::class.java)
        binding.myViewModel = viewModel
        binding.lifecycleOwner = this
    }
}