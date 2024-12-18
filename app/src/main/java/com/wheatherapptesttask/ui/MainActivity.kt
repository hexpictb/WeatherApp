package com.wheatherapptesttask.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.wheatherapptesttask.ui.model.UiState
import com.wheatherapptesttask.ui.view.EmptyView
import com.wheatherapptesttask.ui.view.ErrorScreen
import com.wheatherapptesttask.ui.view.CitiesListScreen
import com.wheatherapptesttask.ui.view.SearchBar
import com.wheatherapptesttask.ui.view.WeatherCard
import com.wheatherapptesttask.ui.view.WeatherTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherTrackerTheme {
                WeatherApp()
            }
        }
    }
}

@Composable
fun WeatherApp() {
    val viewModel: WeatherViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            SearchBar(onSearch = { query -> viewModel.searchCity(query) })
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                when (uiState) {
                    is UiState.Loading -> {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.Center)
                        )
                    }

                    is UiState.SearchResult -> {
                        CitiesListScreen(
                            results = (uiState as UiState.SearchResult).results,
                            onCitySelected = { city -> viewModel.saveCity(city) }
                        )
                    }

                    is UiState.Error -> ErrorScreen((uiState as UiState.Error).message)
                    UiState.Empty -> EmptyView()
                    is UiState.Weather -> WeatherCard(
                        weatherData = (uiState as UiState.Weather).data
                    )
                }
            }

        }
    }
}

