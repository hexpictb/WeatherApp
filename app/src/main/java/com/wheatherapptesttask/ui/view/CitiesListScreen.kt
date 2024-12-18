package com.wheatherapptesttask.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wheatherapptesttask.domain.model.CityItemModel

@Composable
fun CitiesListScreen(
    results: List<CityItemModel>,
    onCitySelected: (CityItemModel) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        LazyColumn {
            items(results) {
                CityCard(it, onClick = { onCitySelected(it) })
                Spacer(modifier = Modifier.height(4.dp))
            }
        }
    }
}