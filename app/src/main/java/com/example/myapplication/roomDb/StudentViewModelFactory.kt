package com.example.myapplication.roomDb

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.roomDb.db.StudentDao

//whenever we have contructor parameter in viewmodel like StudentDao on StudentViewModel we need a viewmodel factory class and provide it to viewModel provider
class StudentViewModelFactory(
    private val dao: StudentDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) //checks if the modelClass is creatable from this viewClass or not
        {

//            Since we know that modelClass is either TaskViewModel or a subclass of it, the type cast is safe. However, the compiler will still generate a warning about an unchecked cast, because the type of T cannot be verified at compile time. The @Suppress("UNCHECKED_CAST") annotation tells the compiler to suppress this warning.
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}