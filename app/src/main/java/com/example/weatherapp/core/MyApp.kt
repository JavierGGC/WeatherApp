package com.example.weatherapp.core

import android.app.Application
import com.example.weatherapp.di.presentationModule
import com.example.weatherapp.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        //timber similar a log
        Timber.d("MyApp_TAG: onCreate: ")
        startKoin { androidContext(this@MyApp)
        modules(repositoryModule + presentationModule)
        }
    }
}