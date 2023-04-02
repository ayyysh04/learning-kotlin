package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

//for this counter app , when we rotate the device ,changes current keyboard ,changes language setting ,enable multi window mode,etc the android lifecycle destroy and rebuild this Activity or a fragment
//in this way application can reload the resources based on newConfiguration
//This may cost very much for large scale app like api fetching every time again and again

//Solution : ViewModel : Model for Activity or fragment i.e VIEW
//ViewModel stays in memory until the app finishes its execution
//ViewModel unclear function is called only when the app is put at the background and the app process is killed to free up the system memory
//Latest SDK doesn't require dependency
class ViewModelActivity : AppCompatActivity() {
    var count = 0
    private lateinit var viewModel: ActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("EXAMPLE_APP", "onCreate ViewModelDemo")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        viewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
//        This will create ViewModels and retain them in a store of the given ViewModelStoreOwne
//        lifecycle owner is the viewModelActivity
        val textView = findViewById<TextView>(R.id.tvCount)
        val button = findViewById<Button>(R.id.btnCount)

        textView.text = viewModel.count.toString()

        button.setOnClickListener {
            viewModel.updateCount()
            textView.text = viewModel.count.toString() //manually updating textView Data
        }

        val liveDataButton = findViewById<Button>(R.id.go_to_live_viewmodel)
        liveDataButton.setOnClickListener {
            val intent = Intent(this, LiveDataActivity::class.java)
            startActivity(intent)

        }

    }


}
