package com.example.myapplication.viewModelScopeUsage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.R

class UserRecyclerViewAdapter(
    private val userList: List<User>,
    private val onTapListener: (User) -> Unit
) : RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.user_item, parent, false)
        return UserViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList[position]
        holder.bind(user, onTapListener)
    }
}

class UserViewHolder(private val view: View) : ViewHolder(view) {
    fun bind(user: User, onTapListener: (User) -> Unit) {

        val myTextView = view.findViewById<TextView>(R.id.userName)
        myTextView.text = user.name

        view.setOnClickListener {
            onTapListener(user)
        }
    }
}