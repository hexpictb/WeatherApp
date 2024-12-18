package com.wheatherapptesttask.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wheatherapptesttask.domain.model.CityItemModel
import com.wheatherapptesttask.data.DataStoreManager
import com.wheatherapptesttask.data.SearchCityRepository
import com.wheatherapptesttask.data.WeatherRepository
import com.wheatherapptesttask.ui.model.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val searchCityRepository: SearchCityRepository,
    private val weatherRepository: WeatherRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    init {
        initState()
    }

    private fun initState() {
        viewModelScope.launch {
            val savedCity = dataStoreManager.savedCity()
            if (savedCity.isEmpty()) {
                _uiState.value = UiState.Empty
            } else {
                loadWeather(savedCity)
            }
        }
    }

    private fun loadWeather(savedCity: String) {
        viewModelScope.launch {
            try {
                val weather = weatherRepository.fetchWeather(savedCity)
                _uiState.value = UiState.Weather(weather)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun searchCity(query: String) {
        if (query.isEmpty()) {
            initState()
        } else {
            _uiState.value = UiState.Loading
            viewModelScope.launch {
                try {
                    val cityItemModels = searchCityRepository.searchCity(query)
                    if (cityItemModels.isEmpty()) {
                        _uiState.value = UiState.Error("No results")
                    } else {
                        _uiState.value = UiState.SearchResult(cityItemModels)
                    }

                } catch (e: Exception) {
                    _uiState.value = UiState.Error(e.message ?: "Unknown error")
                }
            }
        }
    }

    fun saveCity(city: CityItemModel) {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            weatherRepository.saveCity(city.id)
            loadWeather(city.id)
        }
    }
}