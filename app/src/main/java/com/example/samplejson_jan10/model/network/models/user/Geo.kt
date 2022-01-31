package com.example.samplejson_jan10.model.network.models.user

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Geo(
    val lat: String,
    val lng: String
)
