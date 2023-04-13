package com.example.myapplication.livedata.livedataWithDataBinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Example1ViewModel : ViewModel() {

    //encapsulating the data and more security and providing only read access
    private var count: MutableLiveData<Int> = MutableLiveData<Int>()
    val countData: LiveData<Int>
        get() = count

    init {
        count.value = 0
    }

    fun updateCount() {
        count.value = (count.value)?.plus(1)
    }

}