package com.example.weatherapp.presentation.viewmodel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.model.CityInfoModel

class CityInfoAdapter(
    private var cityInfoList: List<CityInfoModel>,
    private val onCityClickListener: (CityInfoModel) -> Unit
) : RecyclerView.Adapter<CityInfoAdapter.CityInfoViewHolder>() {

    class CityInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvState: TextView = itemView.findViewById(R.id.tvState)
        val tvCity: TextView = itemView.findViewById(R.id.tvCity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityInfoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return CityInfoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CityInfoViewHolder, position: Int) {
        val cityInfo = cityInfoList[position]
        holder.tvState.text = cityInfo.state ?: "N/A"
        holder.tvCity.text = cityInfo.name
        holder.itemView.setOnClickListener { onCityClickListener(cityInfo) }
    }

    override fun getItemCount(): Int {
        return cityInfoList.size
    }

    fun updateData(newCityInfoList: List<CityInfoModel>) {
        cityInfoList = newCityInfoList
        notifyDataSetChanged()
    }
}
