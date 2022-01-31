package com.example.samplejson_jan10.model.network.models.user

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)