package com.example.myapplication.viewModelScopeUsage

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.viewModelScopeUsage.recyclerview.UserRecyclerViewAdapter

class UserRepoView : AppCompatActivity() {
    //    get viewModel Instance using viewModel Provider
    private lateinit var userRepoViewModel: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_repo_view)
        userRepoViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        //recycler View variables
        val userRecyclerView: RecyclerView = findViewById<RecyclerView>(R.id.userRecyclerView)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        userRecyclerView.adapter = UserRecyclerViewAdapter(emptyList()) { selectedUser ->
            listItemClicked(
                selectedUser
            )
        }

        userRepoViewModel.getUserData()
//        when the background thread finish fetching all user data ,it will automatically notifies the UI to update the view
        userRepoViewModel.users.observe(this, Observer { myUsers ->
            myUsers.forEach {//can pass this data to recycler view ,but saving time here
                Log.i("MyTag", "name is ${it.name}")
            }

            userRecyclerView.adapter =
                UserRecyclerViewAdapter(myUsers) { selectedUser -> listItemClicked(selectedUser) }
        }) //will observe user live data of userViewModel
    }

    //    passing this function to adapter for click listener
    private fun listItemClicked(user: User) {
        Toast.makeText(
            this@UserRepoView,
            "User is : ${user.name}",
            Toast.LENGTH_LONG
        ).show()
    }
}