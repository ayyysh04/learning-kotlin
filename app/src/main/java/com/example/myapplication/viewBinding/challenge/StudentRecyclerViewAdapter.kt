package com.example.myapplication.viewBinding.challenge

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecyclerViewbindingItemBinding
import com.example.myapplication.roomDb.db.StudentDataClass

class StudentRecyclerViewAdapter(
    private val clickListener: (StudentDataClass) -> Unit
) : RecyclerView.Adapter<StudentViewHolder>() {

    private val studentList = ArrayList<StudentDataClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
//        WE CANT USE JUST LAYOUT INFLATER HERE AS ADAPTER DOESN'T NOT HAVE ITS ONW CONTEXT
//        RecyclerViewbindingItemBinding.inflate(layoutInflater)

        val binding = RecyclerViewbindingItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun setList(students: List<StudentDataClass>) {
        studentList.clear()
        studentList.addAll(students)
    }

}


class StudentViewHolder(private val binding: RecyclerViewbindingItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(student: StudentDataClass, clickListener: (StudentDataClass) -> Unit) {
        binding.apply {
            tvName.text = student.name
            tvEmail.text = student.email
            root.setOnClickListener {
                clickListener(student)
            }
        }
    }
}