package com.example.weatherly.db

import androidx.room.TypeConverter
import com.example.weatherly.rest.model.data_class.Weather
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converter {

    companion object{

        @TypeConverter
        fun stringToWeatherList(value: String?): List<Weather>? {
            val listType: Type = object : TypeToken<List<Weather>?>() {}.type
            return Gson().fromJson(value, listType)
        }

        @TypeConverter
        fun fromArrayList(list: ArrayList<Weather>?): String? {
            val gson = Gson()
            return gson.toJson(list)
        }



    }
}