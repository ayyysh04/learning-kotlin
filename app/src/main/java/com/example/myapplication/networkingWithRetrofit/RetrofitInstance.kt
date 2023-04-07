package com.example.myapplication.networkingWithRetrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    //    In kotlin companion object are initialized when the class is loaded-for the first time i.e singleton
//    Creating the Retrofit instance inside the companion object allows you to access it using the class name rather than an instance of the class. This is because companion objects are associated with the class itself and not with instances of that class.
//If you were to declare the Retrofit instance as a class member, you would need to create an instance of the class in order to access the Retrofit instance. This would not be efficient if you only need one Retrofit instance for the entire application.
//By declaring the Retrofit instance inside the companion object, you can call RetrofitInstance.getRetrofitInstance() to obtain the instance of the Retrofit object without needing to create an instance of the RetrofitInstance class. This ensures that there is only one instance of the Retrofit object throughout the application, which can help to reduce memory usage and improve performance.
//In short, using a companion object to create a singleton Retrofit instance allows you to access it easily and efficiently throughout your application without creating multiple instances.
    companion object {
        private const val BASE_URL: String = "https://jsonplaceholder.typicode.com"

        //        we know retrofit library has created on top of okHttp library ,retrofit uses classes of okhttp to perform network operation
//        this http Logging is a feature belonging to okHttp library which show lots of network operations that happened in our application
//        useful for developer for debugging
        private val interceptor = HttpLoggingInterceptor().apply {
            this.level =
                HttpLoggingInterceptor.Level.BODY //this body level looks request and response lines and their respective headers and bodies of network opeartion
//            other levels like
            //            1.BASIC : look request and response body only
//                    2.HEADER : it will look request and response lines and their respective headers
        }

        //ok http logging interceptor will log the network operation details as android studio logcat messages
//        use OkHttp id on logcat to see the interceptor logs
        private val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                //default timeout value is 10 seconds for each time out
                .connectTimeout(30, TimeUnit.SECONDS) //time to establish connection with the server
                .readTimeout(
                    20,
                    TimeUnit.SECONDS
                )//maximum time gap between arrivals of two data packets when waiting for th server response
                .writeTimeout(
                    25,
                    TimeUnit.SECONDS
                )//maximum time gap between two data packets when sending back to the server
        }.build()

        //this will return the retrofit instance and prevent from creating new instance everytime
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))//convertFactory -> jsonConverterFactor ->to convert json to kotlin
                .build()
        }

    }
}