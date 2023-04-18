package com.example.weatherly.rest.model.data_class


import com.google.gson.annotations.SerializedName

data class Coord(
        @SerializedName("lon")
        val lon: Double = 42.0396042,
        @SerializedName("lat")
        val lat: Double = 9.0128926
)