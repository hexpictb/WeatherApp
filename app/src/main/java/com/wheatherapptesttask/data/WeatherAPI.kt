package com.wheatherapptesttask.data

import com.wheatherapptesttask.data.response.CitySearchResponse
import com.wheatherapptesttask.data.response.WeatherDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("search.json")
    suspend fun searchCity(@Query("q") query: String): List<CitySearchResponse>

    @GET("current.json")
    suspend fun weatherByCityId(@Query("q") query: String): WeatherDataResponse
}
