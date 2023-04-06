package com.example.myapplication.roomDb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.roomDb.db.StudentDataClass

class StudentRecyclerViewAdapter(

    private val clickListener: (StudentDataClass) -> Unit
) : RecyclerView.Adapter<StudentViewHolder>() {

    private val studentDataClassList = ArrayList<StudentDataClass>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.room_list_item, parent, false)
        return StudentViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.bind(studentDataClassList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return studentDataClassList.size
    }

    fun setList(studentDataClasses: List<StudentDataClass>) {
        studentDataClassList.clear()
        studentDataClassList.addAll(studentDataClasses)
    }

}


class StudentViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(studentDataClass: StudentDataClass, clickListener: (StudentDataClass) -> Unit) {
        val nameTextView = view.findViewById<TextView>(R.id.tvName)
        val emailTextView = view.findViewById<TextView>(R.id.tvEmail)
        nameTextView.text = studentDataClass.name
        emailTextView.text = studentDataClass.email
        view.setOnClickListener {
            clickListener(studentDataClass)
        }
    }
}