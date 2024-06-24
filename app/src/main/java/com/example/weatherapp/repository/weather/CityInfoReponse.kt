package com.example.weatherapp.repository.weather

import com.squareup.moshi.Json

data class CityInfoReponse(
    @Json(name = "name") val name: String,
    @Json(name = "state") val state: String,
    @Json(name = "country") val country: String

)
