package com.simon.departurecountdown

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CountdownViewModel : ViewModel() {
    var tripId : String? = null
    var stopName : String? = null
    private var timeLeft = 0

    private val timeLeftData = MutableLiveData<Int>()
    private val delayData = MutableLiveData<Int>()


    fun  getTimeLeftLiveData() : LiveData<Int>{
        startLoop()
        return timeLeftData
    }
    fun  getDelayLiveData() : LiveData<Int>{
        return delayData
    }

    private fun startLoop(){
        viewModelScope.launch(Dispatchers.IO){
            while(true) {
                stopName?.let {
                    val departuresResponse = HttpDao.getDeparturesSynchronous(it)
                    println("Updating delay")
                    departuresResponse?.departures?.forEach { departure ->
                        if (tripId == departure.trip.id) {
                            delayData.postValue(departure.departureTime.getDelay())
                            timeLeft = (Duration.between(
                                LocalDateTime.now(),
                                LocalDateTime.parse(
                                    departure.departureTime.delayedTime,
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss+01:00")
                                )
                            ).toMillis() / 1000).toInt()
                        }
                    }
                }
                delay(30000)
            }
        }

        viewModelScope.launch (Dispatchers.IO){
            while(true) {
                timeLeft--
                timeLeftData.postValue(timeLeft)
                println("Updating time")
                delay(1000)
            }
        }
    }
}