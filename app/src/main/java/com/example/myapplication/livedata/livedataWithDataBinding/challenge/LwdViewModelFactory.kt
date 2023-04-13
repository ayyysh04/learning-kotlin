package com.example.myapplication.livedata.livedataWithDataBinding.challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LwdViewModelFactory(private val startingTotal: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LwdChallengeViewModel::class.java)) {
            return LwdChallengeViewModel(startingTotal) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}