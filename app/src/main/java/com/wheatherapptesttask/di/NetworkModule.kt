package com.wheatherapptesttask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

private const val WEATHER_BASE_URL = "https://api.weatherapi.com/v1/"
private const val WEATHER_API_KEY = "489346e6f3dc44e3b1e232817241612"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideWeatherRetrofit(
        okHttpClient: OkHttpClient,
        kotlinSerializationConverterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(kotlinSerializationConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        val mediaType = "application/json; charset=UTF8".toMediaType()
        return Json { ignoreUnknownKeys = true }
            .asConverterFactory(mediaType)
    }

    @Provides
    fun provideOkhttp3Client(
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .apply { interceptors.forEach { addNetworkInterceptor(it) } }
            .build()

    }

    @Provides
    @IntoSet
    fun provideApiKeyInterceptor(): Interceptor {
        return Interceptor {
            val request = with(it.request()) {
                val url = url.newBuilder()
                    .addQueryParameter("key", WEATHER_API_KEY)
                    .build()
                newBuilder()
                    .url(url)
                    .build()
            }
            it.proceed(request)
        }
    }
}