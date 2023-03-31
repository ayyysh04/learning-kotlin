package com.example.myapplication

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SharedPrefrenceLogic : AppCompatActivity() {
    //    EditText widget for TextInputLayout
//    TextView widget for TextView
    private lateinit var nameText: EditText
    private lateinit var ageText: EditText
    private lateinit var sf: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_prefrences)

        nameText = findViewById<EditText>(R.id.etName)
        ageText = findViewById<EditText>(R.id.etAge)
        sf = getSharedPreferences("my_sf", MODE_PRIVATE)
        editor = sf.edit()
    }

    override fun onPause() {

        super.onPause()
        val name = nameText.text.toString()
        val age = ageText.text.toString().toInt()
//        This is a lambda function -> thats why {} use
/*
eg:  var temp2 = listOf<String>("apple" , "bannana")
        //forEach actual implement :
        //public inline fun tempArray.forEach(action: (Int) -> Unit): Unit {
        //for (element in this) action(element)
        //}
        temp2.forEach {
            fruit->
            println(fruit)
        }
        //here fruit is the iterator and println(fruit) is the action , by default it is the iterator
        //refer lambda_identification_notes.txt
 */
        editor.apply {
            putString("sf_name", name)
            putInt("sf_age", age)
            commit()
        }

    }

    override fun onResume() {
        super.onResume()
        val name = sf.getString("sf_name", null)
        val age = sf.getInt("sf_age", 0)
        nameText.setText(name)
        if (age != 0) {
            ageText.setText(age.toString())
        }
    }
}