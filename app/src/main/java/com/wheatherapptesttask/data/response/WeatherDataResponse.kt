package com.wheatherapptesttask.data.response

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

@Serializable
data class WeatherDataResponse(
    @SerialName("location")
    val location: Location,
    @SerialName("current")
    val current: Current
)

@Serializable
data class Location(
    @SerialName("name")
    val name: String
)

@Serializable
data class Current(
    @SerialName("temp_c")
    val tempInC: Float,
    @SerialName("humidity")
    val humidity: Float,
    @SerialName("feelslike_c")
    val feelsLikeInC: Float,
    @SerialName("uv")
    val uv: Float,
    @SerialName("condition")
    val condition: Condition
)

@Serializable
data class Condition(
    @SerialName("text")
    val text: String,
    @SerialName("icon")
    val icon: String
)
