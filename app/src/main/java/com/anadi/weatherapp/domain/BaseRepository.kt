package com.anadi.weatherapp.domain

interface BaseRepository<T> {
    suspend fun fetchAll(): List<T>

    suspend fun fetch(id: Int): T?

    suspend fun add(obj: T): T

    suspend fun delete(obj: T)

    suspend fun update(obj: T)
}
