package com.wheatherapptesttask.data

import com.wheatherapptesttask.domain.model.WeatherData
import java.text.DecimalFormat
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPI,
    private val dataStoreManager: DataStoreManager
) : WeatherRepository {

    override suspend fun fetchWeather(cityId: String): WeatherData {
        val response = api.weatherByCityId("id:$cityId")
        val decimalFormat = DecimalFormat("#0.##")
        return WeatherData(
            city = response.location.name,
            temperature = decimalFormat.format(response.current.tempInC),
            condition = response.current.condition.text,
            humidity = decimalFormat.format(response.current.humidity),
            uvIndex = decimalFormat.format(response.current.uv),
            feelsLike = decimalFormat.format(response.current.feelsLikeInC),
            iconUrl = "https:${response.current.condition.icon.replace("64", "128")}"
        )
    }

    override suspend fun saveCity(cityId: String) {
        dataStoreManager.saveCity(cityId)
    }
}
