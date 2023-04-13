package com.example.myapplication.livedata.sample2WithFactoryClass

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SumViewModel(startingTotal: Int) : ViewModel() {
    //after private: this will not be visible to the main activity
    private var total = MutableLiveData<Int>()

    //The class has a private variable named "total" that holds the sum of numbers as a MutableLiveData object. The MutableLiveData class is a subclass of LiveData that allows you to change the value of the data it holds.
    //The "totalData" property is a public LiveData object that provides read-only access to the "total" variable. The "get()" method is used to retrieve the current value of "total".
    val totalData: LiveData<Int>
        get() = total//The "totalData" property is defined with a custom getter that returns the value of the "total" variable.

    //In Kotlin, a property can have a custom getter method that is called when the property is accessed. In this case, the custom getter method is defined using the "get()" method.
    //"totalData" property in the code you provided is an example of a Kotlin backing property.
    //In Kotlin, a backing property is a private field that is accessed through a public getter and/or setter method. The purpose of a backing property is to provide a convenient way to encapsulate the state of an object and control access to it.
    //In the case of the "totalData" property, the private field is the "total" variable, which is accessed through the custom getter method defined for the property. The getter method provides read-only access to the "total" variable, which is useful for exposing the sum of numbers as a LiveData object that can be observed for changes.
    //Backing properties are a powerful feature of Kotlin that can simplify code and provide a more concise way to manage object state. By encapsulating the state of an object in a backing property, you can control access to it and ensure that any changes to the state are handled appropriately.

    init {
        total.value = startingTotal
    }

    fun setTotal(input: Int) {
        total.value = (total.value)?.plus(input)
    }
}