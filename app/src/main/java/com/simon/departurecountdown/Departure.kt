package com.simon.departurecountdown

import com.google.gson.annotations.SerializedName

data class Departure(@SerializedName("arrival_timestamp") val departureTime : DepartureTime, val trip : Trip, val route : Route){
    data class Trip(val headsign : String)
    data class Route(@SerializedName("short_name") val name : String)
}
