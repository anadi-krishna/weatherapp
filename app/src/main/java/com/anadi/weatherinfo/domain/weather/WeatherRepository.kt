package com.anadi.weatherinfo.domain.weather

import com.anadi.weatherinfo.data.db.weather.Weather

interface WeatherRepository {

    suspend fun fetchAll(): List<Weather>

    suspend fun fetch(city: String, country: String): Weather?

    suspend fun fetchAllForLocation(id: Long): List<Weather>

    suspend fun fetchAllForProvider(id: Long): List<Weather>

    suspend fun delete(weather: Weather)
}