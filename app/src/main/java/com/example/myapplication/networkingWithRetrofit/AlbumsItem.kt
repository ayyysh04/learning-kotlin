package com.example.myapplication.networkingWithRetrofit

import com.google.gson.annotations.SerializedName

data class AlbumsItem(
    //these id are SerializedName are used in serializing and deserializing i/e json to dataclass and dataclass to json
    @SerializedName("id")
    val id: Int, //we can  give different name than the serialized-name
    @SerializedName("title")
    val title: String,
    @SerializedName("userId")
    val userId: Int
)