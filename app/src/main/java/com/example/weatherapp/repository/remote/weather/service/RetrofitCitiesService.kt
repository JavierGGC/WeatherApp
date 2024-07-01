package com.example.weatherapp.repository.remote.weather.service

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitCitiesService {
    @GET("geo/1.0/direct")
    suspend fun getCityInfo(
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("appid") apiKey: String
    ): Response<List<CityInfoResponse>>

}