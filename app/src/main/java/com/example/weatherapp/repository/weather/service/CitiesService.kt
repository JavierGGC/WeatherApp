package com.example.weatherapp.repository.weather.service

import com.example.weatherapp.data.model.CityInfo

interface CitiesService {

    suspend fun getCitiesInfo(query:String):List<CityInfo>
}