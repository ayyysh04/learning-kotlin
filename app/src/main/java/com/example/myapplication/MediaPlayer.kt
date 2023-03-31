package com.example.myapplication

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MediaPlayer : AppCompatActivity() {
    private lateinit var seekBar: SeekBar
    private var mediaPlayer: MediaPlayer? = null //api in handling audio and video file
    private lateinit var runnable: Runnable
    //In Kotlin, Runnable is an interface that defines a single method called run(), which takes no arguments and returns no value. This interface is used to represent a task that can be executed asynchronously on a separate thread i.e separate from the main thread (UI thread).
    //You can use Runnable in Kotlin to create a new thread that executes a piece of code. For example, suppose you want to perform some long-running task, such as downloading a large file from the internet, without blocking the main thread of your application. You can create a new thread and pass it a Runnable object that contains the code to download the file. The thread will execute the code in the run() method on a separate thread, leaving the main thread free to continue executing other tasks.

    private lateinit var handler: Handler
    // In Kotlin, a Handler is an object that can be used to schedule tasks (runnables) to be executed on a specific thread or after a specific delay. It is often used to communicate between different threads in an Android application, particularly when performing tasks that involve user interface (UI) updates.
//    what is messageQueue ->
// In Android, a MessageQueue is a queue of messages that are sent to a thread for processing. A MessageQueue is associated with a Looper and is used by Handlers to post messages and runnables for processing on a thread.
//When a message or runnable is posted to a Handler, it is added to the message queue associated with the Handler's Looper. The Looper then retrieves messages from the message queue one by one and dispatches them to the appropriate Handler for processing.
//Each message in the MessageQueue has a target Handler, a what field that identifies the type of message, and an optional object field that contains additional data associated with the message.
//By using a MessageQueue and Handler in Android, you can perform background processing and handle messages and runnables in a thread-safe manner. This is particularly useful in the context of Android development, where you often need to perform time-consuming operations on a separate thread to avoid blocking the main UI thread.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medianplayer)
        seekBar = findViewById(R.id.sbClapping)
        handler = Handler(Looper.getMainLooper())
        //In Kotlin, a Looper is used with a Handler to allow a thread to process messages asynchronously. A Looper provides a message queue that allows a thread to process messages one by one, while a Handler is used to send messages to a Looper's message queue for processing.
        //In Kotlin, Looper.getMainLooper() returns the Looper instance associated with the main thread of an Android application. This is the thread where the application's main UI components are created and updated.
        //When using a Handler to perform UI updates, it is important to use the Handler associated with the main thread's Looper to ensure that the updates are performed on the main UI thread. This is because UI updates must be performed on the main thread to avoid errors and ensure smooth performance of the application.

//        button listeners
        val playButton = findViewById<FloatingActionButton>(R.id.fabPlay)
        playButton.setOnClickListener {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.applauding) //create media object
                initializeSeekBar()
            }
            mediaPlayer?.start() //start the media player
        }

        val pauseButton = findViewById<FloatingActionButton>(R.id.fabPause)
        pauseButton.setOnClickListener {
            mediaPlayer?.pause()
        }

        val stopButton = findViewById<FloatingActionButton>(R.id.fabStop)
        stopButton.setOnClickListener {
            mediaPlayer?.stop()

            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
            handler.removeCallbacks(runnable) //remove callback runnable thread ,otherwise it will keep updating the seekbar position when the video is paused only
            seekBar.progress = 0

        }
    }

    //seekbar functions
    private fun initializeSeekBar() {
        /*
        object: is used to create an anonymous object that implements the OnSeekBarChangeListener interface.
        This means that instead of creating a named class that implements the interface, we can create an instance of an unnamed class with the object: syntax. The object: keyword is followed by a colon and the name of the interface or base class that the anonymous object should implement or inherit from.
        The anonymous object is then defined using curly braces {} and contains the implementation of the interface's methods.
         */
//        val mySeekBarChangeListener = MySeekBarChangeListener()
//        seekBar.setOnSeekBarChangeListener(mySeekBarChangeListener)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) mediaPlayer?.seekTo(progress)
                //fromUser : This is a boolean value indicating whether the progress change was initiated by the user or by some other means (such as setting the progress programmatically). If the progress change was initiated by the user, this value will be true. Otherwise, it will be false.
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }


        })
        val tvPlayed = findViewById<TextView>(R.id.tvPlayed)
        val tvDue = findViewById<TextView>(R.id.tvDue)
        seekBar.max = mediaPlayer!!.duration

        runnable = Runnable {
            seekBar.progress =
                mediaPlayer!!.currentPosition //synchronizes the media video progress with seekbar progress

            val playedTime = mediaPlayer!!.currentPosition / 1000 //converting to sec
            tvPlayed.text = "$playedTime sec"
            val duration = mediaPlayer!!.duration / 1000 // converting to sec
            val dueTime = duration - playedTime
            tvDue.text = "$dueTime sec"


            handler.postDelayed(
                runnable,
                1000
            ) //run runnable after 1 sec again to continuously update the seekbar in loop
        }
        handler.postDelayed(runnable, 1000) // call the runnable

    }
}

//Yes, you can create an object that implements the OnSeekBarChangeListener interface separately and pass it as an argument to the setOnSeekBarChangeListener method in Kotlin.
//class MySeekBarChangeListener : SeekBar.OnSeekBarChangeListener {
//    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//        // Handle progress change
//    }
//
//    override fun onStartTrackingTouch(seekBar: SeekBar?) {
//        // Handle start of tracking touch
//    }
//
//    override fun onStopTrackingTouch(seekBar: SeekBar?) {
//        // Handle end of tracking touch
//    }
//}

