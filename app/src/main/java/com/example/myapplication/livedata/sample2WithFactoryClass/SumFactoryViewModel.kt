package com.example.myapplication.livedata.sample2WithFactoryClass

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SumFactoryViewModel(private val startingTotal: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SumViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SumViewModel(startingTotal) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}