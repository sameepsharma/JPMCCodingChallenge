package com.example.weatherly.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherly.Coordinates
import com.example.weatherly.rest.model.data_class.WeatherResponse
import com.example.weatherly.rest.repo.WeatherRepo
import com.example.weatherly.rest.utils.kelvinToCelsius
import com.example.weatherly.rest.utils.timestampToTimeString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(val repo: WeatherRepo) : ViewModel() {


    private val IMG_BASE_URL= "http://openweathermap.org/img/w/"
    private var weatherData = MutableLiveData<WeatherResponse>(WeatherResponse())

    val defaultText = "N/A"

    fun fetchWeather(coordinates: Coordinates) {

        viewModelScope.launch(Dispatchers.IO) {
            weatherData.postValue(repo.getWeatherForLocation(coordinates.lat, coordinates.lon))
        }

    }

    fun getWeatherData() = weatherData

    fun getTempInCelsius() =
        weatherData.value!!.main.temp.kelvinToCelsius().toString().ifEmpty { "N/A" }


    fun getFormattedDateTime(): String =
        SimpleDateFormat("dd MMM, yyyy - hh:mm a", Locale.getDefault()).format(Date())

    fun getWeatherDesc(): String {
        val list = weatherData.value!!.weather
        return if (list.isNotEmpty())
            weatherData.value!!.weather[0].description
        else "N/A"
    }

    fun getHumidity() = weatherData.value!!.main.humidity.toString()
    fun getPressure() = weatherData.value!!.main.pressure.toString() + " mBar"
    fun getMaxTem() = weatherData.value!!.main.tempMax.kelvinToCelsius().toString() + "°C MAX"
    fun getMinTemp() = weatherData.value!!.main.tempMin.kelvinToCelsius().toString() + "°C MIN"

    fun getSunrise() = weatherData.value!!.sys.sunrise.timestampToTimeString()
    fun getSunset() = weatherData.value!!.sys.sunset.timestampToTimeString()

    fun getWeatherIconURL() : String
    {

        val list = weatherData.value!!.weather
        return if (list.isNotEmpty()) {
            Log.e("IMGURL = ", "$IMG_BASE_URL${weatherData.value!!.weather[0].icon}.png")
            "$IMG_BASE_URL${weatherData.value!!.weather[0].icon}.png"

        }
        else
            ""
    }



}