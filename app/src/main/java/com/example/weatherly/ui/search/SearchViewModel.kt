package com.example.weatherly.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherly.rest.model.data_class.CityResponse
import com.example.weatherly.rest.repo.WeatherRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchViewModel(val repo: WeatherRepo) : ViewModel() {
    private val mText: MutableLiveData<String>

    private val cityResponse = MutableLiveData<CityResponse>()

    init {
        mText = MutableLiveData()
        mText.value = "Type City Name"
    }

    val text: LiveData<String>
        get() = mText

    fun getCityResponse() = cityResponse

    fun getCity(q: String) {
        viewModelScope.launch(Dispatchers.IO) {
            cityResponse.postValue(repo.getCity(q))
        }
    }
}