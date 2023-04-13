package com.example.myapplication.livedata.twoWayDataBinding

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TwoWayViewModel : ViewModel() {
    val userName = MutableLiveData<String>()

    init {
        userName.value = "Frank"
    }

}