package com.wheatherapptesttask.domain.model

data class WeatherData(
    val iconUrl: String,
    val city: String,
    val temperature: String,
    val condition: String,
    val humidity: String,
    val uvIndex: String,
    val feelsLike: String
)