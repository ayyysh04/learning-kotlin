package com.example.myapplication.viewModel.challenge

import androidx.lifecycle.ViewModel

//to use parameterized view model ,we have to create a factory class first
class SumViewModel(startingTotal: Int) : ViewModel() {
    private var total = 0

    init {
        total = startingTotal
    }

    fun getTotal(): Int {
        return total
    }

    fun setTotal(input: Int) {
        total += input
    }
}