package com.sameep.iiflassignment.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherly.db.Data

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(json: Data)

    @Query("select json from city_data")
    fun getSavedData(): String

/*

    @Query("select jsonRevGeo from rev_geo_data")
    fun getSavedData(): String
*/

}