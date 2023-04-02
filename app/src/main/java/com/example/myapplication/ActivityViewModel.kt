package com.example.myapplication

//import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {
    var count = 0
//    var count = MutableLiveData<Int>()

    init {
//        count.value = 0
        count = 0
    }

    fun updateCount() {
        ++count
//        count.value = (count.value)?.plus(1)
    }

}