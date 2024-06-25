package com.example.weatherapp.repository.remote.weather.service

import com.example.weatherapp.core.Session
import com.example.weatherapp.data.model.CityInfo
import com.example.weatherapp.repository.remote.decodeResponse

class CitiesServiceImpl(private val retrofitCitiesService: RetrofitCitiesService) : CitiesService {
    override suspend fun getCitiesInfo(query: String): List<CityInfo> {
        return retrofitCitiesService.getCityInfo(query, 5, Session.API_KEY)
            .decodeResponse { citiesListResponse ->
                citiesListResponse.map { cityResponse ->
                    CityInfo(
                        name = cityResponse.name,
                        state = cityResponse.state,
                        country = cityResponse.country
                    )
                }
            }
    }


}