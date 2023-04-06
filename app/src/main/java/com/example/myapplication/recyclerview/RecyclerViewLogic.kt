package com.example.myapplication.recyclerview

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

class RecyclerViewLogic : AppCompatActivity() {
    //List of data class
    val fruitsList = listOf<Fruit>(
        Fruit("Mango", "Joe"),
        Fruit("Apple", "Frank"),
        Fruit("Banana", "Tom"),
        Fruit("Guava", "Joe"),
        Fruit("Lemon", "Alex"),
        Fruit("Pear", "Joe"),
        Fruit("Orange", "Alex")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recycler_view_activity)
        val recyclerView = findViewById<RecyclerView>(R.id.myRecyclerView)
        recyclerView.setBackgroundColor(Color.YELLOW) //background color of recycler view
        recyclerView.layoutManager = LinearLayoutManager(this)
        //layout manager :for different layout
//        linear layout manager
//        grid layout manager
//        staggered layout manager

        //adapter instance to setting up data in view
        recyclerView.adapter = MyRecyclerViewAdapter(
            fruitsList,
        ) { selectedItem: Fruit -> //lambda expression to pass function
            listItemClicked(selectedItem)
        }
    }

    //    passing this function to adapter for click listener
    private fun listItemClicked(fruit: Fruit) {
        Toast.makeText(
            this@RecyclerViewLogic,
            "Supplier is : ${fruit.supplier}",
            Toast.LENGTH_LONG
        ).show()
    }


}