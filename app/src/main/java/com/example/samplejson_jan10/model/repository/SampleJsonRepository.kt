package com.example.samplejson_jan10.model.repository

import com.example.samplejson_jan10.model.network.SampleJsonService
import com.example.samplejson_jan10.model.network.models.Post
import com.example.samplejson_jan10.model.network.models.Todo
import com.example.samplejson_jan10.model.network.models.user.User
import com.example.samplejson_jan10.utils.Resource
import javax.inject.Inject

class SampleJsonRepository @Inject constructor(
    private val sampleJsonService: SampleJsonService
) {
    suspend fun getTodos(): Resource<List<Todo>> {
        return try {
            val response = sampleJsonService.getTodos()
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Failed to get Todos.")
            }
        } catch (ex: Exception) {
            Resource.Error("unexpected error")
        }
    }

    suspend fun getPosts(): Resource<List<Post>> {
        return try {
            val response = sampleJsonService.getPosts()
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Failed to get Posts.")
            }
        } catch (ex: Exception) {
            Resource.Error("unexpected error")
        }
    }

    suspend fun getUsers(): Resource<List<User>> {
        return try {
            val response = sampleJsonService.getUsers()
            if (response.isSuccessful && response.body() != null) {
                Resource.Success(response.body()!!)
            } else {
                Resource.Error("Failed to get Users.")
            }
        } catch (ex: Exception) {
            Resource.Error("unexpected error")
        }
    }
}
