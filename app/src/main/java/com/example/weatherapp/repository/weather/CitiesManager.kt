package com.example.weatherapp.repository.weather

import com.example.weatherapp.repository.toPretty
import com.example.weatherapp.repository.weather.service.CitiesService
import retrofit2.http.Query

class CitiesManager(private val citiesService: CitiesService) {
    suspend fun getCities(query: String) = runCatching { citiesService.getCitiesInfo(query) }.toPretty()
}