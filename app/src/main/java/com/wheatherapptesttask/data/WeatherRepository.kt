package com.wheatherapptesttask.data

import com.wheatherapptesttask.domain.model.WeatherData

interface WeatherRepository {
    suspend fun fetchWeather(cityId: String): WeatherData
    suspend fun saveCity(cityId: String)
}