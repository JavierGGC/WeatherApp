package com.example.weatherapp.di

import com.example.weatherapp.model.ForecastResponse
import com.example.weatherapp.presentation.viewmodel.ForecastViewModel
import com.example.weatherapp.repository.remote.weather.CitiesManager
import com.example.weatherapp.repository.remote.weather.ForecastManager
import com.example.weatherapp.repository.remote.weather.service.CitiesService
import com.example.weatherapp.repository.remote.weather.service.CitiesServiceImpl
import com.example.weatherapp.repository.remote.weather.service.RetrofitCitiesService
import com.example.weatherapp.repository.remote.weather.service.RetrofitForecastService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


private val logLevel =
    if (!BuildConfig.DEBUG) HttpLoggingInterceptor.Level.NONE
    else HttpLoggingInterceptor.Level.BODY

fun createOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = logLevel })
    .readTimeout(120, TimeUnit.SECONDS)
    .build()

val apiModule = module {
    single { createOkHttpClient() }
}

inline fun <reified T> createRetrofitWebService(okHttpClient: OkHttpClient, url: String): T =
    Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(T::class.java)

val citiesServiceModule = module {
    single<RetrofitCitiesService> {
        createRetrofitWebService(
            get(),
            "http://api.openweathermap.org/"
        )
    }
    single<CitiesService> { CitiesServiceImpl(get()) }
    single { CitiesManager(get()) }

    single { ForecastManager(get()) }

}

val viewModelModule = module {
    viewModel { ForecastViewModel(get()) }
}


val repositoryModule = listOf(apiModule, citiesServiceModule, viewModelModule)