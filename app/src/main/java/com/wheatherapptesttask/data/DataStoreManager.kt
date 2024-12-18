package com.wheatherapptesttask.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    suspend fun savedCity(): String = dataStore.data
        .map { preferences -> preferences[stringPreferencesKey("city")] ?: "" }
        .first()

    suspend fun saveCity(cityId: String) {
        dataStore.edit { preferences -> preferences[stringPreferencesKey("city")] = cityId }
    }
}
