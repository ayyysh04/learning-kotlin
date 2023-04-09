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
import com.example.myapplication.androidServices.AndroidServiceActivity
import com.example.myapplication.coroutinesActivities.CoroutinesActivity
import com.example.myapplication.fragments.NavigationActivity
import com.example.myapplication.navigationComponents.NavigationComponentActivity
import com.example.myapplication.networkingWithRetrofit.RetrofitActivity
import com.example.myapplication.notificationComponents.NotificationDemoActivity
import com.example.myapplication.recyclerview.RecyclerViewLogic
import com.example.myapplication.roomDb.RoomDbActivity
import com.example.myapplication.viewBinding.ViewBindingActivity
import com.example.myapplication.viewBinding.challenge.RecyclerViewBinding
import com.example.myapplication.viewModel.ViewModelActivity
import com.example.myapplication.viewModelScopeUsage.UserRepoView

//AppCompactactivity : provides lifecycle override function like onCreate
class MainLogic : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //savedInstance is the bundle or previous state which was paused if any
        super.onCreate(savedInstanceState)//overrides the parent class
        setContentView(R.layout.main_screen)//set the xml desgin on screen
//        R.layout to access XML file
//        R.id to access id assigned to XML componenets
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
        val recyclerViewButton = findViewById<Button>(R.id.go_to_recyclerView)
        val coroutinesViewButton = findViewById<Button>(R.id.go_to_coroutinesView)
        val viewModelScopeUseCaseButton = findViewById<Button>(R.id.go_to_viewModeScopeUseCase)
        val roomDbButton = findViewById<Button>(R.id.go_to_roomDb)
        val viewBindingButton = findViewById<Button>(R.id.go_to_viewBinding)
        val recyclerViewBindingButton = findViewById<Button>(R.id.go_to_recyclerViewBinding)
        val androidServiceButton = findViewById<Button>(R.id.go_to_androidServiceView)
        val retrofitNetworkingButton = findViewById<Button>(R.id.go_to_retrofitNetworkView)
        val notificationButton = findViewById<Button>(R.id.go_to_NotificationView)
        val fragmentNavButton = findViewById<Button>(R.id.go_to_fragmentNav)
        val navigationComponentsButton = findViewById<Button>(R.id.go_to_navigationComponents)
        var enteredName = ""
//        setOnClickListener to listen to changes on button
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
//In Android development, there are two types of contexts: ApplicationContext and ActivityContext.
//
//The ApplicationContext is a global context for the entire application, and it is available throughout the lifecycle of the application. It is commonly used for operations that require a context beyond the scope of an activity, such as accessing system services, registering broadcast receivers, and creating new threads.
//On the other hand, the ActivityContext is a context that is tied to a specific activity. It provides access to the activity's resources, such as layouts, strings, and themes, and it is used to create and manage UI components within the activity, such as views, dialogs, and notifications.
//It's important to note that while both ApplicationContext and ActivityContext are Context objects, they have different lifecycles and usage patterns. In general, it is recommended to use the ApplicationContext when possible, and to use the ActivityContext only when necessary for operations specific to a particular activity.
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
            val intent = Intent(this, SharedPreferenceLogic::class.java)
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
        recyclerViewButton.setOnClickListener {
            val intent = Intent(this, RecyclerViewLogic::class.java)
            startActivity(intent)
        }
        coroutinesViewButton.setOnClickListener {
            val intent = Intent(this, CoroutinesActivity::class.java)
            startActivity(intent)
        }
        viewModelScopeUseCaseButton.setOnClickListener {
            val intent = Intent(this, UserRepoView::class.java)
            startActivity(intent)
        }
        roomDbButton.setOnClickListener {
            val intent = Intent(this, RoomDbActivity::class.java)
            startActivity(intent)
        }
        viewBindingButton.setOnClickListener {
            val intent = Intent(this, ViewBindingActivity::class.java)
            startActivity(intent)
        }
        androidServiceButton.setOnClickListener {
            val intent = Intent(this, AndroidServiceActivity::class.java)
            startActivity(intent)
        }
        recyclerViewBindingButton.setOnClickListener {
            val intent = Intent(this, RecyclerViewBinding::class.java)
            startActivity(intent)
        }
        retrofitNetworkingButton.setOnClickListener {
            val intent = Intent(this, RetrofitActivity::class.java)
            startActivity(intent)
        }
        notificationButton.setOnClickListener {
            val intent = Intent(this, NotificationDemoActivity::class.java)
            startActivity(intent)
        }
        fragmentNavButton.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }
        navigationComponentsButton.setOnClickListener {
            val intent = Intent(this, NavigationComponentActivity::class.java)
            startActivity(intent)
        }

    }
}
