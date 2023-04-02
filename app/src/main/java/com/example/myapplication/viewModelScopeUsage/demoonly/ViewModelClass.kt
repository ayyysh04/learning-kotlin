package com.example.myapplication.viewModelScopeUsage.demoonly

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ViewModelClass : ViewModel() {

    //    job instance to access the context of coroutine scope
    private val myJob = Job()

    //coroutine scope
    private val myScope = CoroutineScope(Dispatchers.IO + myJob)

    fun getUserData() {
        myScope.launch {
        }
    }

    //everytime the viewModel is cleared from the memory ,view-model invokes its uncleared method
//    some of the coroutines have potential to run even after the viewModel is cleared from the memory and may run until app gets terminated
//    we need to cancel before clearing view-model
    override fun onCleared() {
        super.onCleared()
        myJob.cancel() //cancel coroutine -> but we can avoid this manual task for each viewModel -> use viewModelScope
//        viewModelScope is bounded to view-model lifecycle ,it has created to automatic cancellation when then viewmodel nnCleared is called
    }
}