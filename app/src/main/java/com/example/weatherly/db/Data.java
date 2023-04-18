package com.example.weatherly.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city_data")
public class Data {


    public Data(String json){
        this.json = json;
    }
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    String json = "";
    @PrimaryKey(autoGenerate = true) public int id;

}
