package com.example.myapplication.viewModelScopeUsage

import kotlinx.coroutines.delay

//local store -> real world -> we use apis
class UserRepository {
    suspend fun getUsers(): List<User> {
        delay(8000) //to mimic a long running task -> dealing this by 8 sec
        val users: List<User> = listOf(
            User(1, "Sam"),
            User(2, "Taro"),
            User(3, "Gero"),
            User(4, "Muku"),
        )
        return users
    }
}