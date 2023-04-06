package com.example.myapplication.roomDb

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.roomDb.db.StudentDataClass
import com.example.myapplication.roomDb.db.StudentDatabase

class RoomDbActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var clearButton: Button
    private lateinit var viewModel: StudentViewModel
    private lateinit var studentRecyclerView: RecyclerView
    private lateinit var adapter: StudentRecyclerViewAdapter
    private var isListItemClicked = false

    private lateinit var selectedStudentDataClass: StudentDataClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roomdb)

        nameEditText = findViewById(R.id.etName)
        emailEditText = findViewById(R.id.etEmail)
        saveButton = findViewById(R.id.btnSave)
        clearButton = findViewById(R.id.btnClear)
        studentRecyclerView = findViewById(R.id.rvStudent)

        val dao = StudentDatabase.getInstance(application).studentDao()
        val factory = StudentViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory).get(StudentViewModel::class.java)

        //use database inspector to see if the data is getting saved or not
        saveButton.setOnClickListener {
            if (isListItemClicked) {
                updateStudentData()
                clearInput()
            } else {
                saveStudentData()
                clearInput()
            }
        }

        clearButton.setOnClickListener {
            if (isListItemClicked) {
                deleteStudentData()
                clearInput()
            } else {
                clearInput()
            }
        }

        initRecyclerView()


    }

    private fun saveStudentData() {
        viewModel.insertStudent(
            StudentDataClass(
                0,//id will be incremented automatically -> ignore it
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
    }

    private fun updateStudentData() {
        viewModel.updateStudent(
            StudentDataClass(
                selectedStudentDataClass.id,//requires id to update that data
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
        saveButton.text = "Save"
        clearButton.text = "Clear"
        isListItemClicked = false
    }

    private fun deleteStudentData() {
        viewModel.deleteStudent(
            StudentDataClass(
                selectedStudentDataClass.id,
                nameEditText.text.toString(),
                emailEditText.text.toString()
            )
        )
        saveButton.text = "Save"
        clearButton.text = "Clear"
        isListItemClicked = false
    }

    private fun clearInput() {
        nameEditText.setText("")
        emailEditText.setText("")
    }

    private fun initRecyclerView() {

        studentRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = StudentRecyclerViewAdapter { selectedItem: StudentDataClass ->
            listItemClicked(selectedItem)
        }
        studentRecyclerView.adapter = adapter

        displayStudentsList()
    }

    private fun displayStudentsList() {
        viewModel.students.observe(this) {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
//            notifyDataSetChanged() is a method in Android's RecyclerView class that informs the adapter that the underlying dataset has changed and that it needs to be updated.
//When you call this method, it triggers a full update of the RecyclerView, which means that all of the visible views in the RecyclerView will be recreated, and the adapter will be asked to provide new data for each view.
//It is typically used when you need to update the entire dataset of the RecyclerView at once. For example, if you have a list of items and you want to add or remove items from it, you would call notifyDataSetChanged() to update the RecyclerView.
//However, it is important to note that calling notifyDataSetChanged() can be resource-intensive and can result in a less performant user experience. As an alternative, you can use more specific methods like notifyItemInserted(), notifyItemRemoved(), and notifyItemChanged() to update individual items in the RecyclerView without triggering a full update.
        }
    }

    private fun listItemClicked(studentDataClass: StudentDataClass) {
//        Toast.makeText(
//            this,
//            "Student name is ${student.name}",
//            Toast.LENGTH_LONG
//        ).show()
        selectedStudentDataClass = studentDataClass
        saveButton.text = "Update"
        clearButton.text = "Delete"
        isListItemClicked = true
        nameEditText.setText(selectedStudentDataClass.name)
        emailEditText.setText(selectedStudentDataClass.email)

    }
}















