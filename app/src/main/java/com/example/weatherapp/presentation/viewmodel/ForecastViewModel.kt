package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.ForecastResponse
import com.example.weatherapp.repository.remote.weather.ForecastManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastViewModel(private val forecastManager: ForecastManager) : ViewModel() {

    private val _forecastResponse = MutableLiveData<ForecastResponse>()
    val weatherResponse: LiveData<ForecastResponse>
        get() = _forecastResponse

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun fetchWeatherForecast(lat: Double, lon: Double) {
        val call = forecastManager.getWeatherForecast(lat, lon)
        call.enqueue(object : Callback<ForecastResponse> {
            override fun onResponse(call: Call<ForecastResponse>, response: Response<ForecastResponse>) {
                if (response.isSuccessful) {
                    _forecastResponse.value = response.body()
                } else {
                    _errorMessage.value = "Failed to retrieve data"
                }
            }

            override fun onFailure(call: Call<ForecastResponse>, t: Throwable) {
                _errorMessage.value = "Request failed: ${t.message}"
            }
        })
    }
}