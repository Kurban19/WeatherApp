package com.shkiper.whether

import com.shkiper.whether.models.WeatherResult
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.http.Query

interface IOpenWeatherMap {

    @GET("weather")
    fun getWeatherByLatLng(@Query("lat")lat: String,
                            @Query("lon")lon: String,
                            @Query("appid")appid: String,
                            @Query("units")unit: String): Observable<WeatherResult>
}