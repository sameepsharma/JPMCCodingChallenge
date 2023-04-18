package com.example.weatherly.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.weatherly.rest.repo.WeatherRepo

class HomeViewModelFactory(val repo: WeatherRepo) : NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repo = repo) as T
    }
}