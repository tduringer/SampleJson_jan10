package com.example.todosapi_jan10.model.network.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
)