package com.example.weatherapp.repository.weather.service

import android.app.DownloadManager.Query
import com.example.weatherapp.core.Session
import com.example.weatherapp.data.model.CityInfo
import com.example.weatherapp.repository.decodeResponse

class CitiesServiceImpl(private val retrofitCitiesService: RetrofitCitiesService):CitiesService {
    override suspend fun getCitiesInfo(query: String): List<CityInfo> {
        return retrofitCitiesService.getCityInfo(query, 5, Session.API_KEY).decodeResponse {
            it.map { CityInfo(it.name, it.state, it.country) }
        }
    }


}