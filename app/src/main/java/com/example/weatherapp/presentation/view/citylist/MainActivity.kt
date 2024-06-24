package com.example.weatherapp.presentation.view.citylist

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.BR
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityDetailBinding
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.presentation.viewmodel.CityInfoAdapter
import com.example.weatherapp.presentation.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var cityInfoAdapter: CityInfoAdapter
    private val weatherViewModel by viewModel<WeatherViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, weatherViewModel)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        cityInfoAdapter = CityInfoAdapter(emptyList())
        binding.recyclerView.adapter = cityInfoAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    weatherViewModel.fetchCityInfo(it)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

        weatherViewModel.cityInfoList.observe(this, Observer { cityInfoList ->
            if (cityInfoList.isEmpty()) {
                weatherViewModel.messageVisible.postValue(true)
            } else
                weatherViewModel.messageVisible.postValue(false)

            cityInfoAdapter.updateData(cityInfoList)
        })

        weatherViewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }
}