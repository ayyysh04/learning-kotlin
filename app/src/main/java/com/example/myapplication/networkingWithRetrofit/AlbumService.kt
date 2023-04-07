package com.example.myapplication.networkingWithRetrofit

//url : https://jsonplaceholder.typicode.com/albums
//baseurl of api : https://jsonplaceholder.typicode.com
//endpoint : /albums
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface AlbumService {
    //normal request
    @GET("/albums") //endpoint
//    used suspend as we are going to use coroutines with retrofit
//    if we are not willing to use coroutines ,we don't need to add suspend modifier
    suspend fun getAlbums(): Response<Albums>
//    retrofit always give the result as retrofit response object of a type
//    we decide the type by looking at the json -> as json is of the format array and dataclass is of Array of Album items -> response type is Albums i.e the name of the dataclass which represents the entire json object

    //query request : eg : https://jsonplaceholder.typicode.com/albums?userId=2 return all albums with userId 2
//    Query value is userId with parameter name userId and type /int
    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<Albums>

    //pathQuery : eg -https://jsonplaceholder.typicode.com/albums/3 return all albums with id 3
//    Path value is id with parameter name albumId and type Int
    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") albumId: Int): Response<AlbumsItem>

}
