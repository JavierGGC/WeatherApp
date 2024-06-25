package com.example.weatherapp.repository.remote.weather.service

import com.squareup.moshi.Json

data class CityInfoResponse(
    @Json(name = "name") val name: String,
    @Json(name = "state") val state: String,
    @Json(name = "country") val country: String
)
