package com.simon.departurecountdown

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object HttpDao {
        private val retrofit = Retrofit.Builder()
            .baseUrl("https://api.golemio.cz/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val departures = MutableLiveData<DeparturesResponse>()
        fun getDepartures(stopName : String): LiveData<DeparturesResponse> {
            val api = retrofit.create(GolemioApi::class.java)
            val call = api.getStopDepartures(stopName, 120, BuildConfig.api_key)
            call.enqueue(object :Callback<DeparturesResponse>{
                override fun onResponse(call: Call<DeparturesResponse>, response: Response<DeparturesResponse>
                ) {
                    if(response.isSuccessful) {
                        departures.postValue(response.body())
                    }else{
                        Log.e("HTTP",""+response.code())
                    }
                }

                override fun onFailure(call: Call<DeparturesResponse>, t: Throwable) {
                    t.printStackTrace()
                }

            })
            return departures
        }

}