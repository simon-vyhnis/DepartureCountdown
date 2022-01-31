package com.simon.departurecountdown

import com.google.gson.annotations.SerializedName

data class DepartureTime(@SerializedName("scheduled") val regularTime : String, @SerializedName("predicted") val delayedTime : String)
