package com.example.weatherly

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherly.rest.model.data_class.CityRevGeoCode
import com.example.weatherly.rest.repo.WeatherRepo
import kotlinx.coroutines.launch

class
ActivityViewModel(private val repo: WeatherRepo) : ViewModel() {

    private val searchedLoc = MutableLiveData<String>()

    private val locRevGeoCode = MutableLiveData<CityRevGeoCode>()

    private val toolbarTitle = MutableLiveData<String>()

    private val isSearched = MutableLiveData(false)

    fun observeTitle() = toolbarTitle

    fun updateTitle(title: String) = toolbarTitle.postValue(title)

    private var coordinates = MutableLiveData<Coordinates>()

    fun updateLatlong(lati: Double, longi: Double) = coordinates.postValue(Coordinates(lati, longi))

    fun observeCoordinates() = coordinates
    fun printLog(msg: String) {
        Log.e(ActivityViewModel::class.java.toString(), "PRINTING LOG = $msg")
    }

    fun getSearchedCity() = searchedLoc

    fun updateLoc(loc: String) {
        searchedLoc.postValue(loc)
    }

    fun getLocRevGeoCode(coordinates: Coordinates) {
        viewModelScope.launch {
            val revGeoresp = repo.cityRevGeoCode(coordinates)!!
            locRevGeoCode.postValue(revGeoresp)
            searchedLoc.postValue("${ revGeoresp[0].name }, ${revGeoresp[0].country}")
        }
    }

    fun getLocGeoCode() = locRevGeoCode

    fun getIsSearched() = isSearched.value!!
    fun setIsSearched(value : Boolean) = isSearched.postValue(value)
}



data class Coordinates(val lat: Double, val lon: Double)