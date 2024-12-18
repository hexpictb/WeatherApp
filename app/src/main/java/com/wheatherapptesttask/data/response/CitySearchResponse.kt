package com.wheatherapptesttask.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CitySearchResponse(
    @SerialName("id") val id: Long,
    @SerialName("name") val name: String,
    @SerialName("region") val region: String,
    @SerialName("country") val country: String,
    @SerialName("lat") val lat: Float,
    @SerialName("lon") val lon: Float,
    @SerialName("url") val url: String
)