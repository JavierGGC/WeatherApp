package com.example.weatherapp.core

import android.app.Application
import timber.log.Timber

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        //timber similar a log
        Timber.d("MyApp_TAG: onCreate: ")
    }
}