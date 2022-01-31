package com.example.samplejson_jan10.model.network

class ApiManager {

    private var sampleJsonService: SampleJsonService =
        RetrofitInstance.providesRetrofit().create(SampleJsonService::class.java)

    suspend fun getTodos() = sampleJsonService.getTodos()

    suspend fun getUsers() = sampleJsonService.getUsers()

    suspend fun getPosts() = sampleJsonService.getPosts()

    suspend fun getPhotos() = sampleJsonService.getPhotos()



}