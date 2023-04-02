package com.example.myapplication.viewModelScopeUsage.demoonly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ViewModelScopeClass : ViewModel() {

    fun getUserData() {
//        android ktx provide lambdas ,extension function and extension properties eg viewModelScope
        viewModelScope.launch {

//            Now no need to overwrite onCleared function ,it will be handled automatically
        }
    }
}