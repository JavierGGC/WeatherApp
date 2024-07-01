package com.example.weatherapp.presentation.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.CityInfoModel
import com.example.weatherapp.model.WeatherItem
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ForecastInfoAdapter (
    private var weatherList: List<WeatherItem>) :
    RecyclerView.Adapter<ForecastInfoAdapter.WeatherViewHolder>() {

    fun updateData(newWeatherList: List<WeatherItem>) {
        weatherList = newWeatherList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_weather_second, parent, false)
        return WeatherViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherItem = weatherList[position]

        //Verificando que no sea nulo
        if (weatherItem.mainDetails != null) {
            holder.tvMax.text = "${weatherItem.mainDetails.temp_max}°C"
            holder.tvMin.text = "${weatherItem.mainDetails.temp_min}°C"
        } else {
            // Manejar el caso donde mainDetails es nulo
            holder.tvMax.text = "N/A"
            holder.tvMin.text = "N/A"
        }

        holder.tvDateHour.text = SimpleDateFormat("EEE, hh:mm a", Locale.getDefault()).format(Date(weatherItem.dt * 1000))

        val iconUrl = "http://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
        Picasso.get().load(iconUrl).into(holder.ivIcon)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivIcon: ImageView = itemView.findViewById(R.id.ivIcon)
        val tvDateHour: TextView = itemView.findViewById(R.id.tvDateHour)
        val tvMax: TextView = itemView.findViewById(R.id.tvMax)
        val tvMin: TextView = itemView.findViewById(R.id.tvMin)
    }
}

