package com.simon.departurecountdown

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GolemioApi {

    @GET("/v2/pid/departureboards/?preferredTimezone=Europe%2FPrague&mode=departures&order=real&skip=canceled")
    fun getStopDepartures(@Query("names") stopName: String, @Query("minutesAfter") minutesAfter : Int, @Header("x-access-token") key : String) : Call<DeparturesResponse>
}