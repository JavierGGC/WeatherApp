package com.example.weatherapp.model

data class CityInfoModel(
    val name: String,
    val state: String?,
    val country: String,
    val lat: Double,
    val lon: Double
)