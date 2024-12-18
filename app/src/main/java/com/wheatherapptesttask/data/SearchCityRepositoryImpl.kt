package com.wheatherapptesttask.data

import com.wheatherapptesttask.domain.model.CityItemModel
import javax.inject.Inject

class SearchCityRepositoryImpl @Inject constructor(
    private val weatherAPI: WeatherAPI
) : SearchCityRepository {

    override suspend fun searchCity(city: String): List<CityItemModel> {
        val responses = weatherAPI.searchCity(city)
        return responses.map {
            with(it) {
                CityItemModel("$id", name, region, country)
            }
        }
    }
}