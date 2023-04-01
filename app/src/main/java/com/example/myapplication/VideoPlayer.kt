package com.example.myapplication

import android.net.Uri
import android.net.Uri.parse
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class VideoPlayer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_player)

        val videoView = findViewById<VideoView>(R.id.videoView)
        val mediaController =
            MediaController(this)//MediaPlayer class can be used to control playback of audio/video files and streams.
        // Set the position of the MediaController relative to the anchor view

        mediaController.setAnchorView(videoView)
//setAnchorView() is a method in the MediaController class in Android that sets the view that the media controller will be anchored to. The media controller is a user interface element that provides controls for playing and pausing media playback, adjusting the volume, and seeking through the media.
//When you create a MediaController instance, you can use the setAnchorView() method to attach the media controller to a specific view in your app's user interface. The media controller will be positioned relative to the anchor view, and will automatically hide and show itself based on user interactions.
//In Android, an anchor view is a view that another view is attached to and positioned relative to. The MediaController in Android is an example of a view that can be attached to an anchor view.
//When you set the anchor view for a MediaController using the setAnchorView() method, you are telling the MediaController to attach itself to the specified view and position itself relative to that view. The position of the MediaController is determined by the layout parameters of the anchor view.
//In the case of a VideoView, the anchor view is typically set to be the VideoView itself, so that the MediaController appears directly above or below the VideoView. By changing the layout parameters of the anchor view, you can adjust the position of the MediaController relative to the VideoView.
//Overall, the anchor view serves as a reference point for positioning and attaching the MediaController or other views in Android.
//ctrl + alt + y to refresh or File -> Reload all from list
//for gradle refresh : FIle -> sync project with gradle files

        val uri: Uri = parse("android.resource://" + packageName + "/" + R.raw.beracuda)
//        for online : Uri.parse("https://example.com/myvideo.mp4")
//        for storage : val uri = Uri.parse("file:///sdcard/myvideo.mp4")s
//In Android, a Uri (Uniform Resource Identifier) is a reference to a resource or file. The VideoView class is used to display video content in an Android application, and requires a Uri to specify the location of the video file to be played.
//The Uri can reference a video file located on the device's storage, on a network server, or in the app's resources. By using a Uri, the VideoView can easily retrieve the video file and display it on the screen.
        videoView.setVideoURI(uri)
        videoView.setMediaController(mediaController)
        //In Android, the setMediaController() method is used to attach a MediaController instance to a VideoView instance. The MediaController is a UI component that provides playback controls for the VideoView, such as play, pause, and seek.
        videoView.requestFocus()
//Without calling requestFocus(), the VideoView may not respond to user input events such as touch events or key events. This can make it difficult or impossible for the user to perform actions such as pausing or resuming the video playback.
//In some cases, the VideoView may automatically request focus if it is the only focusable view in the layout. However, it's generally a good practice to explicitly call requestFocus() on the VideoView instance to ensure that it receives focus and can respond to user input events.
        videoView.start()//start playing video automatically


    }
}