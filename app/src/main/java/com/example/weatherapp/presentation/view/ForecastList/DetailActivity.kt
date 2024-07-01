package com.example.weatherapp.presentation.view.ForecastList

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityDetailBinding
import com.example.weatherapp.presentation.viewmodel.ForecastInfoAdapter
import com.example.weatherapp.presentation.viewmodel.ForecastViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    private val forecastViewModel: ForecastViewModel by viewModel()
    private lateinit var forecastAdapter: ForecastInfoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cityName = intent.getStringExtra("cityName") ?: ""
        val lat = intent.getDoubleExtra("lat", 0.0)
        val lon = intent.getDoubleExtra("lon", 0.0)

        findViewById<TextView>(R.id.tvCityDetail).text = cityName

        val recyclerView: RecyclerView = findViewById(R.id.rvWeather)
        binding.rvWeather.layoutManager = LinearLayoutManager(this)
        forecastAdapter = ForecastInfoAdapter(emptyList())
        recyclerView.adapter = forecastAdapter

        forecastViewModel.fetchWeatherForecast(lat, lon)

        forecastViewModel.weatherResponse.observe(this, { weatherResponse ->
            forecastAdapter.updateData(weatherResponse.list)
        })

        forecastViewModel.errorMessage.observe(this, { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }
}