package com.example.myapplication
/*
Organization: By grouping related classes, functions, and other elements into a package, you can organize your code in a way that makes it easier to understand and maintain. This can be particularly useful in larger projects where there are many different components that need to work together.
Encapsulation: Packages can be used to control access to the classes and functions within them. By making certain classes or functions private, you can prevent them from being accessed outside of the package. This can help to enforce encapsulation and reduce the risk of unintended side-effects.
Modularity: By separating your code into packages, you can create modular components that can be reused in other projects. This can help to reduce duplication of code and make it easier to maintain and update your codebase over time
 */
import android.content.Intent
import android.os.Bundle
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//AppCompactactivity : provides lifecycle override function like onCreate
class MainLogic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //savedInstance is the bundle or previous state which was paused if any
        super.onCreate(savedInstanceState)//overrides the parent class
        setContentView(R.layout.main_screen)//set the xml desgin on screen
        val greetingTextView =
            findViewById<TextView>(R.id.tvHello)//findViewById to get access to the element by id
//        R is a global varibale
        val inputField = findViewById<EditText>(R.id.etName)
        val submitButton = findViewById<Button>(R.id.btnSubmit)
        val offersButton = findViewById<Button>(R.id.btnOffers)
        val sharedPrefsButton = findViewById<Button>(R.id.go_to_sharedprefs)
        val bmiCalButton = findViewById<Button>(R.id.go_to_bmiCal)
        val mediaPlayerButton = findViewById<Button>(R.id.go_to_mediaPLayer)
        val videoPlayerButton = findViewById<Button>(R.id.go_to_videoPlayer)
        val viewModelButton = findViewById<Button>(R.id.go_to_ViewModel)
        var enteredName = ""
//        setOnClickListner to listen to changes on button
        submitButton.setOnClickListener {
            enteredName = inputField.text.toString()
            if (enteredName == "") {
                offersButton.visibility = INVISIBLE
                greetingTextView.text = ""
//                Toast to show a notification
                Toast.makeText(
//                    uses context : you can use this or this@MainLogic or applicationContext
//                    but applicationContext is linked with the context of whole app lifecycle
//                    whereas this gives the context of this activity only

                    this,
                    "Please, enter your name!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val message = "Welcome $enteredName"
                greetingTextView.text = message
                inputField.text.clear()
                offersButton.visibility = VISIBLE
            }
        }

        offersButton.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("USER", enteredName)
            startActivity(intent)
        }

        sharedPrefsButton.setOnClickListener {
            val intent = Intent(this, SharedPrefrenceLogic::class.java)
            startActivity(intent)
        }
        bmiCalButton.setOnClickListener {
            val intent = Intent(this, BmiCalculator::class.java)
            startActivity(intent)
        }
        mediaPlayerButton.setOnClickListener {
            val intent = Intent(this, MediaPlayer::class.java)
            startActivity(intent)
        }
        videoPlayerButton.setOnClickListener {
            val intent = Intent(this, VideoPlayer::class.java)
            startActivity(intent)
        }
        viewModelButton.setOnClickListener {
            val intent = Intent(this, ViewModelActivity::class.java)
            startActivity(intent)
        }

    }
}
