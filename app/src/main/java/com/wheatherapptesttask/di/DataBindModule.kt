package com.wheatherapptesttask.di

import com.wheatherapptesttask.data.SearchCityRepository
import com.wheatherapptesttask.data.SearchCityRepositoryImpl
import com.wheatherapptesttask.data.WeatherRepository
import com.wheatherapptesttask.data.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataBindModule {
    @Binds
    abstract fun bindSearchRepository(impl: SearchCityRepositoryImpl): SearchCityRepository

    @Binds
    abstract fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository
}