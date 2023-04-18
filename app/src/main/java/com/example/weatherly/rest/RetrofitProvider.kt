package com.example.weatherly.rest

import android.util.Log
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//https://randomuser.me/api/?results=10

object RetrofitProvider {

    var retrofit: Retrofit? = null
        get() {

            if (field == null) {



                val client = OkHttpClient.Builder().connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build()

                field = Retrofit.Builder().baseUrl("https://api.openweathermap.org/")
                    .client(OkHttpClient().newBuilder().addInterceptor(Interceptor {

                        Log.e("REQUEST URL :" , "URL = ${it.request().url()}")

                        it.proceed(it.request())

                    }).build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return field

        }

    private set

    fun getLoggin(): HttpLoggingInterceptor? {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

}