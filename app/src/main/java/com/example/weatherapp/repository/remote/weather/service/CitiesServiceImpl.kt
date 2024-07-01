package com.example.weatherapp.repository.remote.weather.service

import com.example.weatherapp.core.Session
import com.example.weatherapp.model.CityInfoModel
import com.example.weatherapp.repository.remote.decodeResponse

class CitiesServiceImpl(private val retrofitCitiesService: RetrofitCitiesService) : CitiesService {
    override suspend fun getCitiesInfo(query: String): List<CityInfoModel> {
        return retrofitCitiesService.getCityInfo(query, 5, Session.API_KEY)
            .decodeResponse { citiesListResponse ->
                citiesListResponse.map { cityResponse ->
                    CityInfoModel(
                        name = cityResponse.name,
                        state = cityResponse.state,
                        country = cityResponse.country,
                        lat = cityResponse.lat,
                        lon = cityResponse.lon
                    )
                }
            }
    }


}