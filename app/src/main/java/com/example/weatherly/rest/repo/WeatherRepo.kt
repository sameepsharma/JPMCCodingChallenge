package com.example.weatherly.rest.repo

import android.content.Context
import android.util.Log
import com.example.weatherly.Coordinates
import com.example.weatherly.db.Data
import com.example.weatherly.rest.FetchService
import com.example.weatherly.rest.model.data_class.CityResponse
import com.example.weatherly.rest.model.data_class.CityRevGeoCode
import com.example.weatherly.rest.model.data_class.WeatherResponse
import com.example.weatherly.rest.utils.isInternetAvailable
import com.google.gson.Gson
import com.sameep.iiflassignment.db.dao.WeatherDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class WeatherRepo(
    private val fetchService: FetchService,
    private val weatherDao: WeatherDao,
    val context: Context
) {

    val API_KEY = "a77de1fd89a0ee9f0ed2c85e6a1c750b"

    suspend fun getWeatherForLocation(latitude: Double, longitude: Double): WeatherResponse? {
        return if (context.isInternetAvailable()) {
            val articlesNw = fetchService.getWeatherData(latitude, longitude, API_KEY)
             weatherDao.insertArticles(Data(Gson().toJson(articlesNw!!).toString()))
            return (articlesNw)
        } else {
           getSavedWeather()
        }

    }

    suspend fun getSavedWeather() : WeatherResponse?{
        var dbItems = ""
        withContext(Dispatchers.IO){
            dbItems = weatherDao.getSavedData()
        }
        Log.e("REpo ::", "DBItems = $dbItems")
        if (dbItems.isNotEmpty())
            return Gson().fromJson(dbItems, WeatherResponse::class.java)
        else return null
    }

    suspend fun getCity(query: String, limit: Byte = 5): CityResponse? {

        return if (context.isInternetAvailable()) {
            fetchService.getCity(query, limit, API_KEY)
        } else null

    }

    suspend fun cityRevGeoCode(coordinates: Coordinates, limit: Byte = 5): CityRevGeoCode? {
        return if (context.isInternetAvailable())
            fetchService.getCityByCoordinates(coordinates.lat, coordinates.lon, limit, API_KEY)
        else
            null
    }

}