package com.example.samplejson_jan10.model.network

import com.example.samplejson_jan10.model.network.models.Photo
import com.example.samplejson_jan10.model.network.models.Post
import com.example.samplejson_jan10.model.network.models.Todo
import com.example.samplejson_jan10.model.network.models.user.User
import retrofit2.Response
import retrofit2.http.GET

interface SampleJsonService {

    @GET("todos")
    suspend fun getTodos(): Response<List<Todo>>

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("photos")
    suspend fun getPhotos(): Response<List<Photo>>
}