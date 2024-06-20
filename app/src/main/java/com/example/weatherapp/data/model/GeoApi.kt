package com.example.weatherapp.data.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GeoApi {
    @GET("geo/1.0/direct")
    fun getCityInfo(
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("appid") apiKey: String
    ): Call<List<CityInfo>>
}