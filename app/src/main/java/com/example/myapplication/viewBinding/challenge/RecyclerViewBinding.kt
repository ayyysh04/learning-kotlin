package com.example.myapplication.viewBinding.challenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityRecyclerViewBindingBinding
import com.example.myapplication.roomDb.StudentViewModel
import com.example.myapplication.roomDb.StudentViewModelFactory
import com.example.myapplication.roomDb.db.StudentDataClass
import com.example.myapplication.roomDb.db.StudentDatabase

class RecyclerViewBinding : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBindingBinding
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: StudentRecyclerViewAdapter
    private var isListItemClicked = false

    private lateinit var selectedStudent: StudentDataClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBindingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dao = StudentDatabase.getInstance(application).studentDao()
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(StudentViewModel::class.java)

        binding.apply {
            btnSave.setOnClickListener {
                if (isListItemClicked) {
                    updateStudentData()
                    clearInput()
                } else {
                    saveStudentData()
                    clearInput()
                }
            }

            btnClear.setOnClickListener {
                if (isListItemClicked) {
                    deleteStudentData()
                    clearInput()
                } else {
                    clearInput()
                }
            }
        }

        initRecyclerView()


    }

    private fun saveStudentData() {
        binding.apply {
            viewModel.insertStudent(
                StudentDataClass(
                    0,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )
        }
    }

    private fun updateStudentData() {

        binding.apply {
            viewModel.updateStudent(
                StudentDataClass(
                    selectedStudent.id,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )
            btnSave.text = "Save"
            btnClear.text = "Clear"
            isListItemClicked = false
        }
    }

    private fun deleteStudentData() {
        binding.apply {
            viewModel.deleteStudent(
                StudentDataClass(
                    selectedStudent.id,
                    etName.text.toString(),
                    etEmail.text.toString()
                )
            )
            btnSave.text = "Save"
            btnClear.text = "Clear"
            isListItemClicked = false
        }
    }

    private fun clearInput() {
        binding.apply {
            etName.setText("")
            etEmail.setText("")
        }
    }

    private fun initRecyclerView() {

        binding.rvStudent.layoutManager = LinearLayoutManager(this)
        adapter = StudentRecyclerViewAdapter { selectedItem: StudentDataClass ->
            listItemClicked(selectedItem)
        }
        binding.rvStudent.adapter = adapter


        displayStudentsList()
    }

    private fun displayStudentsList() {
        viewModel.students.observe(this, {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(student: StudentDataClass) {
        binding.apply {
            selectedStudent = student
            btnSave.text = "Update"
            btnClear.text = "Delete"
            isListItemClicked = true
            etName.setText(selectedStudent.name)
            etEmail.setText(selectedStudent.email)
        }

    }
}
