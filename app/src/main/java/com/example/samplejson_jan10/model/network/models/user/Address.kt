package com.example.samplejson_jan10.model.network.models.user

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: Geo
)