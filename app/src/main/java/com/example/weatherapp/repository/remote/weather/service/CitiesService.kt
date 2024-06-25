package com.example.weatherapp.repository.remote.weather.service

import com.example.weatherapp.model.CityInfoModel

interface CitiesService {

    suspend fun getCitiesInfo(query: String): List<CityInfoModel>
}