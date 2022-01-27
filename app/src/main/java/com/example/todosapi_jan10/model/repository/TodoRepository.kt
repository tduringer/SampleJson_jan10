package com.example.todosapi_jan10.model.repository

import com.example.todosapi_jan10.model.network.ApiManager
import com.example.todosapi_jan10.model.network.models.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoRepository(
    private val apiManager: ApiManager
) {
    suspend fun getTodos(): List<Todo>? {
            return try {
                val response = apiManager.getTodos()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    emptyList()
                }
            } catch (ex: Exception) {
                emptyList()
            }
    }

}