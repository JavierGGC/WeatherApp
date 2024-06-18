package com.example.weatherapp.repository.weather

sealed class CompletedPretty {
    abstract val success: Boolean

    object Success : CompletedPretty() {
        override val success: Boolean
            get() = true
    }

    class Error(val throwable: Throwable) : CompletedPretty() {
        override val success: Boolean
            get() = false
    }
}