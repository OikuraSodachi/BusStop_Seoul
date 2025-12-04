package com.todokanai.domain

import retrofit2.http.GET
import retrofit2.http.Query

interface BusArriveService {

    @GET("getStationByUidItem?serviceKey=")
    fun getStationArrive(@Query("arsId") arsId: String )
}