package com.simon.departurecountdown

import com.google.gson.annotations.SerializedName

data class Arrival(@SerializedName("scheduled") val regularTime : String, @SerializedName("predicted") val delayedTime : String)
