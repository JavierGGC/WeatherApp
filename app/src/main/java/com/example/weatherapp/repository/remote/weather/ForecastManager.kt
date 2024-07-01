package com.example.weatherapp.repository.remote.weather

import com.example.weatherapp.core.Session.API_KEY
import com.example.weatherapp.model.ForecastResponse
import com.example.weatherapp.repository.remote.weather.service.RetrofitForecastService
import retrofit2.Call

class ForecastManager(private val retrofitForecastWeather: RetrofitForecastService) {

    fun getWeatherForecast(lat: Double, lon: Double): Call<ForecastResponse> {
        return retrofitForecastWeather.getWeatherForecast(lat, lon, API_KEY)
    }
}