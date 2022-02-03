package com.simon.departurecountdown

import com.google.gson.annotations.SerializedName
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class DepartureTime(@SerializedName("scheduled") val regularTime : String, @SerializedName("predicted") val delayedTime : String){
    fun getDelay() : Int{
        val time1 = LocalDateTime.parse(regularTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+01:00"))
        val time2 = LocalDateTime.parse(delayedTime, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+01:00"))
        return Duration.between(time2, time1).toMinutes().toInt()
    }
}
