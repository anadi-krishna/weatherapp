package com.anadi.weatherinfo.data

import com.anadi.weatherinfo.data.data.WeatherInfo
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.Serializable
import kotlin.jvm.Throws

class LocationInfo(var cityName: String, var country: Country) : Serializable {
    var id: Int
        private set
    lateinit var info: WeatherInfo

    override fun equals(other: Any?): Boolean {
        if (other !is LocationInfo) {
            return false
        }
        return other.cityName == cityName && other.country == country && other.info == info
    }

    override fun toString(): String {
        return "$cityName, $country"
    }

    @Throws(IOException::class)
    private fun writeObject(oos: ObjectOutputStream) {
        oos.defaultWriteObject()
    }

    @Throws(ClassNotFoundException::class, IOException::class)
    private fun readObject(ois: ObjectInputStream) {
        ois.defaultReadObject()
        id = count++
    }

    companion object {
        private var count = 0
    }

    init {
        id = count++
    }
}