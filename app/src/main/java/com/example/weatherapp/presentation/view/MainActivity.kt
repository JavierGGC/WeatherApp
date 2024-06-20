package com.example.weatherapp.presentation.view

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.presentation.viewmodel.CityInfoAdapter
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var cityInfoAdapter: CityInfoAdapter
    private val cityViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView: SearchView = findViewById(R.id.searchView)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        cityInfoAdapter = CityInfoAdapter(emptyList())
        recyclerView.adapter = cityInfoAdapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    cityViewModel.fetchCityInfo(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        cityViewModel.cityInfoList.observe(this, Observer { cityInfoList ->
            cityInfoAdapter.updateData(cityInfoList)
        })

        cityViewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }
}