package com.example.myapplication.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataActivityViewModel : ViewModel() {
    var count = MutableLiveData<Int>()
//    MutableLiveData is a subclass of the LiveData class in the Android Architecture Components library. As the name suggests, it allows the data held by the LiveData object to be modified.
//    While LiveData provides a read-only view of the data, MutableLiveData adds a setValue() method that allows the data to be updated. It's often used as a backing field for a ViewModel to hold data that can be updated in response to user actions or other events.

    init {
        count.value = 0
    }

    fun updateCount() {
        count.value = (count.value)?.plus(1)
    }

}