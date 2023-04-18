package com.example.weatherly.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.example.weatherly.rest.repo.WeatherRepo

class SearchViewModelFactory(val repo: WeatherRepo) : NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(repo = repo) as T
    }
}