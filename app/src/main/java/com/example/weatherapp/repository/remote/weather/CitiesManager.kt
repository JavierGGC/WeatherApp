package com.example.weatherapp.repository.remote.weather

import com.example.weatherapp.repository.remote.weather.service.CitiesService
import com.example.weatherapp.repository.toPretty

class CitiesManager(private val citiesService: CitiesService) {
    suspend fun getCities(query: String) =
        runCatching { citiesService.getCitiesInfo(query) }.toPretty()
}