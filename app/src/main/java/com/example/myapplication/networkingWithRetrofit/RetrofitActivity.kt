package com.example.myapplication.networkingWithRetrofit


import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.myapplication.databinding.ActivityRetrofitBinding
import retrofit2.Response

class RetrofitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetrofitBinding
    private lateinit var retService: AlbumService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)
        getRequestWithQueryParameters() //now using coroutine live data builder we will get the retrofit response as live data

        //       getRequestWithPathParameters() //for querying like getting sorted data or particular id data
//        eg : https://jsonplaceholder.typicode.com/albums?userId=2

    }


    private fun getRequest() {
//        livedata : kotlin coroutine livedata builder
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getAlbums()
            emit(response)
        }
        responseLiveData.observe(this, Observer { response ->
//            response is the retrofit response object of type albums ,which contains an arraylist of albumsItem object which is converted from json to kotlin by the GsonConverterFactory
            val albumsList = response.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    val result = " " + "Album Title : ${albumsItem.title}" + "\n" +
                            " " + "Album id : ${albumsItem.id}" + "\n" +
                            " " + "User id : ${albumsItem.userId}" + "\n\n\n"
                    binding.textView.append(result)
                }
            }
        })
    }

    private fun getRequestWithQueryParameters() {
//        livedata : kotlin coroutine livedata builder
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            val response = retService.getSortedAlbums(3)
            emit(response) //emit() is used to publish new data to the LiveData object, which will then notify any active observers that the data has changed. It is typically called inside a coroutine block, so that long-running or blocking operations can be executed on a background thread while still being able to update the UI.
        }
        responseLiveData.observe(this, Observer { response ->
//            response is the retrofit response object of type albums ,which contains an arraylist of albumsItem object which is converted from json to kotlin by the GsonConverterFactory
            val albumsList = response.body()?.listIterator()
            if (albumsList != null) {
                while (albumsList.hasNext()) {
                    val albumsItem = albumsList.next()
                    val result = " " + "Album Title : ${albumsItem.title}" + "\n" +
                            " " + "Album id : ${albumsItem.id}" + "\n" +
                            " " + "User id : ${albumsItem.userId}" + "\n\n\n"
                    binding.textView.append(result)
                }
            }
        })
    }

    private fun getRequestWithPathParameters() {
        val pathResponse: LiveData<Response<AlbumsItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)

        }

        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext, title, Toast.LENGTH_LONG).show()
        })
    }

}