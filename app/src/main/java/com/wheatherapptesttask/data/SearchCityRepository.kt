package com.wheatherapptesttask.data

import com.wheatherapptesttask.domain.model.CityItemModel

interface SearchCityRepository {
    suspend fun searchCity(city: String): List<CityItemModel>
}