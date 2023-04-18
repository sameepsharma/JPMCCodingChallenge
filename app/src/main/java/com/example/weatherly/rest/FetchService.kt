package com.example.weatherly.rest

import com.example.weatherly.rest.model.data_class.CityResponse
import com.example.weatherly.rest.model.data_class.CityRevGeoCode
import com.example.weatherly.rest.model.data_class.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FetchService {

    @GET("data/2.5/weather")
    suspend fun getWeatherData(@Query("lat") lat :Double
                            , @Query("lon") lon :Double
                            , @Query("appid") apiKey :String) : WeatherResponse

    @GET("geo/1.0/direct")
    suspend fun getCity(@Query("q") query:String
                        ,@Query("limit") limit : Byte
                        ,@Query("appid") apiKey: String) : CityResponse

    @GET("geo/1.0/reverse")
    suspend fun getCityByCoordinates(@Query("lat") lat:Double
                                     ,@Query("lon") lon:Double
                                     ,@Query("limit") limit : Byte
                        ,@Query("appid") apiKey: String) : CityRevGeoCode

}