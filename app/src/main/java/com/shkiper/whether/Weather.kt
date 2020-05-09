package com.shkiper.whether

data class Weather(
    val id: Int,
    val city: String,
    var currentTemp: String,
    var description: String
) {

}