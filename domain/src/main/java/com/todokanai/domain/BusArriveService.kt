package com.todokanai.domain

import retrofit2.http.GET
import retrofit2.http.Query

interface BusArriveService {

    @GET()
    fun getStationArrive(@Query("") nodeId: String )
}