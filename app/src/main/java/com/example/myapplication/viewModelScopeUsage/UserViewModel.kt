package com.example.myapplication.viewModelScopeUsage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//liveData
class UserViewModel : ViewModel() {

    private val userRepository = UserRepository()

    //going to send this list to the views as liveData
    var users: MutableLiveData<List<User>> = MutableLiveData()
    fun getUserData() {
        viewModelScope.launch {
            var result: List<User>
//            invoking getUser function of userRepository which is a long running task using coroutine
            withContext(Dispatchers.IO) {
                result = userRepository.getUsers()
            }
            users.value = result
        }

    }
}