package com.wheatherapptesttask.ui.model

import com.wheatherapptesttask.domain.model.CityItemModel
import com.wheatherapptesttask.domain.model.WeatherData

sealed class UiState {
    data object Loading : UiState()
    data class SearchResult(val results: List<CityItemModel>) : UiState()
    data class Weather(val data: WeatherData) : UiState()
    data object Empty : UiState()
    data class Error(val message: String) : UiState()
}