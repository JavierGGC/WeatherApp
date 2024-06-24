package com.example.weatherapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.model.CityInfo
import com.example.weatherapp.repository.handle
import com.example.weatherapp.repository.weather.CitiesManager
import com.example.weatherapp.repository.weather.CityRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class WeatherViewModel(private val citiesManager: CitiesManager) : ViewModel() {

    private val _cityInfoList = MutableLiveData<List<CityInfo>>()
    val cityInfoList: LiveData<List<CityInfo>>
        get() = _cityInfoList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    val messageVisible = MutableLiveData(false)

    fun fetchCityInfo(query: String) {
//        val call = cityRepository.getCityInfo(query)
//        call.enqueue(object : Callback<List<CityInfo>> {
//            override fun onResponse(call: Call<List<CityInfo>>, response: Response<List<CityInfo>>) {
//                if (response.isSuccessful) {
//                    _cityInfoList.value = response.body()
//                } else {
//                    _errorMessage.value = "Failed to retrieve data"
//                }
//            }

//            override fun onFailure(call: Call<List<CityInfo>>, t: Throwable) {
//                _errorMessage.value = "Request failed: ${t.message}"
//            }
//        })
        CoroutineScope(Dispatchers.IO).launch {
            citiesManager.getCities(query).handle(
                error = { t ->
                    _errorMessage.value = "Request failed: ${t.message}"

                }, success = { list ->
                    _cityInfoList.value = list
                })
        }
    }

}