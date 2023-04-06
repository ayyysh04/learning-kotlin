package com.example.myapplication.roomDb


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.roomDb.db.StudentDao
import com.example.myapplication.roomDb.db.StudentDataClass
import kotlinx.coroutines.launch

class StudentViewModel(private val dao: StudentDao) : ViewModel() {

    val students = dao.getAllStudents()

    //creating viewModelScope to create a Coroutine tied to viewModel lifecycle and run the operation
    fun insertStudent(studentDataClass: StudentDataClass) = viewModelScope.launch {
        dao.insertStudent(studentDataClass)
    }

    fun updateStudent(studentDataClass: StudentDataClass) = viewModelScope.launch {
        dao.updateStudent(studentDataClass)
    }

    fun deleteStudent(studentDataClass: StudentDataClass) = viewModelScope.launch {
        dao.deleteStudent(studentDataClass)
    }


}