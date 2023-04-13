package com.example.myapplication.viewModel.challenge

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SumViewModelFactoryClass(private val startingTotal: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SumViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SumViewModel(startingTotal) as T
        }

        throw IllegalArgumentException("Unknown View model class")
    }

}