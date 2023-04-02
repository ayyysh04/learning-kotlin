package com.example.myapplication.coroutinesActivities

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.coroutines.*

class CoroutinesActivity : AppCompatActivity() {
    private var count = 0
    private lateinit var messageTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines)

        val textView = findViewById<TextView>(R.id.tvCount)
        val countButton = findViewById<Button>(R.id.btnCount)
        val downloadButtonMain = findViewById<Button>(R.id.btnDownload)
        val downloadButtonSeparate = findViewById<Button>(R.id.btnDownload2)
        messageTextView = findViewById<TextView>(R.id.tvMessage)
        countButton.setOnClickListener {
            textView.text = count++.toString()
        }
        //try click the button and then click the count button : the ui will start lagging or crash as we are doing long task in main thread only ,u can also see it in log cat
//        Main thread is also called UI thread and used for ui related tasks
        downloadButtonMain.setOnClickListener {
            downloadUserDataNormal()
        }

        //try click the button and then click the count button : the ui will not lag or crash this time as we are doing long task in separate thread only ,u can also see it in log cat
//        threads : main thread ,on-demand background worker threads,and both can have many coroutines executing in them at same time
        downloadButtonSeparate.setOnClickListener {
            //Dispatcher as coroutine context -> used as the explicit job instance
//            Dispatcher defines the kind of threat via coroutine should be run , In kotlin android structured concurrency it is always recommended to start coroutines using main threat and then switch to background threat
//            Dispatcher.Main : run in main(UI) threat and there is only one main threat -> used for small lightweight tasks like go to ui function ,go to suspending function ,to get updates from the liveData
//            Dispatcher.IO : run in background thread from a shared pool of on-demand created threads -> used to communicate with local database ,communicate with network ,or to work with files
//            Dispatcher.Default : used for CPU intensive tasks such as sorting a large list with 10000 data items or passing huge jso file with details of 10000 movies
//            Dispatcher.Unconfined : used with global scope -> coroutine run in main thread ,but if it is suspended and resumed it will run on suspending function's thread -> not recommend to used in android development
//            convert executors to dispatchers
//            to create our own custom dispatcher ,libraries like room ,retrofit use custom dispatchers to execute operation on separate background thread -> so no need to write coroutine code for this libraries
            CoroutineScope(Dispatchers.IO).launch {
//                read about type of coroutine builder in types of coroutines builder.txt
                downloadUserData()
            }
//            GlobalScope.launch {  }
        }
        //coroutineScope is a suspending function in Kotlin that creates a new coroutine scope. A coroutine scope is a construct that defines the lifecycle of a set of coroutines, allowing them to be launched and cancelled together. The scope is used to manage the lifecycle of the coroutines within it, including their cancellation when the scope is cancelled.
//        As there can be 1000's of coroutines running at same time but bydefault couroutines doesn't help us to keep a tack of them or keep track or any work done by them ,if we do not manage them properly -> there will be coroutine leaks which leads to resource exploitation and also the performance of the application
//        to fix this problem we have to start all coroutines within the scope and using the properties belong to the scope we can easily keep a track of coroutines ,cancel coroutines and handle errors or exception thrown by the coroutines -> done using CoroutinesScope
//        another scope -> GlobalScope : In Kotlin coroutines, the GlobalScope is a pre-defined, global instance of the CoroutineScope class that can be used to launch coroutines that have a global, top-level scope. The GlobalScope is similar to launching a coroutine without any specified scope, but with the added benefit that it is managed by the framework and automatically cancelled when the application exits.
//                                       The GlobalScope is typically used for launching coroutines that have a long-running, global scope, such as background services or network requests that should continue running throughout the lifecycle of the application.
        // This code launches a new coroutine in Kotlin using the launch function within a CoroutineScope. The CoroutineScope is created with the Dispatchers.IO context, which is typically used for performing I/O-bound operations, such as reading or writing to a file, or making a network request.
        //The launch function starts a new coroutine that runs asynchronously, allowing the rest of the program to continue running without waiting for the coroutine to complete. Within the coroutine, the downloadUserData() function is called, which likely performs some operation to download user data, such as making a network request to an API.
    }

    //we can declare function inside or outside of the main class without any problem -> if we want our function to access class members declare them inside it
//suspend function can only be called from coroutines or another suspend function
    private suspend fun downloadUserData() //simulate like real life long running task like api fetching
    {
        for (i in 1..10000000) {
            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
            delay(1000)
//        In Kotlin coroutines, delay is a suspending function that causes a coroutine to suspend its execution for a specified amount of time, without blocking the thread it is running on. It is similar to the Thread.sleep function in Java, but it is non-blocking, allowing other coroutines or threads to continue executing while the coroutine is suspended.

//        Changing thread of coroutine
//            messageTextView.text = "Downloading user $i"
//            In Android ,we can direclty go to view component run in ui thread from a background thread like this -> if we run it will crash showing a code from wrong thread exception//
//            Only the UI thread can access the views
            withContext(Dispatchers.Main)
            {
                messageTextView.text = "Downloading user $i"
            }
            //In the above code, withContext is a suspending function that switches the context of a coroutine to a different dispatcher, which is specified as its argument. In this case, the Dispatchers.Main dispatcher is used, which is typically used for performing operations that update the user interface (UI) of the application, such as modifying text or layout properties of views.
            //Within the withContext block, the messageTextView.text property is updated to display a message indicating that user data is being downloaded.
            //By switching to the Dispatchers.Main dispatcher context, the code ensures that the UI update is performed on the main thread of the application, which is required for proper UI rendering and interaction.
            //It is important to note that withContext is a suspending function, and can only be called from within a coroutine or another suspending function. When withContext is called, the coroutine is suspended and the execution of the coroutine is resumed on the specified dispatcher context. Once the block within withContext is complete, the coroutine is resumed on its original dispatcher context.
        }
    }

    private fun downloadUserDataNormal() {
        for (i in 1..10000000) {
            Log.i("MyTag", "Downloading user $i in ${Thread.currentThread().name}")
        }
        //LOGCAT MAY STOP AS THE it was unable to read the messages as quickly as they were introduced ,we can avoid this issue by slowing down the result by using a delay function of coroutines library
        //need to mark function as suspend keyword when using delay inside it  or using a modified function with suspend keyword
        //showed in above function
    }
}
