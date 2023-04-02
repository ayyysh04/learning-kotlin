package com.example.myapplication.viewModelScopeUsage.demoonly

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

//when working with android jetpack , we follow MVVM architecture
//to create ViewModel for activities and fragments
class MvvmViewModel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_mvvm_view_model)
    }
}