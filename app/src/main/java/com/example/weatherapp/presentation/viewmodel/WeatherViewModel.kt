package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.model.CityInfoModel
import com.example.weatherapp.repository.handle
import com.example.weatherapp.repository.remote.weather.CitiesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val citiesManager: CitiesManager) : ViewModel() {

    private val _cityInfoList = MutableLiveData<List<CityInfoModel>>()
    val cityInfoList: LiveData<List<CityInfoModel>>
        get() = _cityInfoList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    val messageVisible = MutableLiveData(false)

    fun fetchCityInfo(query: String) {
        CoroutineScope(Dispatchers.IO).launch {
            citiesManager.getCities(query).handle(
                error = { t ->
                    _errorMessage.postValue("Request failed: ${t.message}")

                }, success = { list ->
                    _cityInfoList.postValue(list)
                })
        }
    }

}