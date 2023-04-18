package com.example.weatherly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.weatherly.rest.repo.WeatherRepo

class ActivityViewModelFactory(private val repo: WeatherRepo) : NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActivityViewModel(repo = repo) as T
    }
}
