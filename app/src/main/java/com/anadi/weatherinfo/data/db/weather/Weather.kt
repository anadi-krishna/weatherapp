package com.anadi.weatherinfo.data.db.weather

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.anadi.weatherinfo.data.db.location.Location

@Entity(tableName = "weather")
data class Weather(
        @PrimaryKey(autoGenerate = true)
        val weatherId: Int,

        @ForeignKey(entity = Location::class, parentColumns = ["id"], childColumns = ["locationId"], onDelete = ForeignKey.CASCADE)
        @ColumnInfo(name = "locationId")
        val locationId: Int,

        @ColumnInfo(name = "providerId")
        val providerId: Int,

        @ColumnInfo(name = "code")
        val code: Int,

        @ColumnInfo(name = "temp")
        val temp: Int,

        @ColumnInfo(name = "temp_feels_like")
        val tempFeelsLike: Int,

        @ColumnInfo(name = "wind_speed")
        val windSpeed: Int,

        @ColumnInfo(name = "wind_degree")
        val windDegree: Int,

        @ColumnInfo(name = "pressure")
        val pressure: Int,

        @ColumnInfo(name = "humidity")
        val humidity: Int,

        @ColumnInfo(name = "clouds")
        val clouds: Int,

        @ColumnInfo(name = "data_calc_timestamp")
        val dataCalcTimestamp: Long,

        @ColumnInfo(name = "download_timestamp")
        val downloadTimestamp: Long
)