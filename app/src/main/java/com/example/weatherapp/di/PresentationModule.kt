package com.example.weatherapp.di

import com.example.weatherapp.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val citiesModule = module {
    viewModel { WeatherViewModel(get()) }
}
val presentationModule = listOf(citiesModule)