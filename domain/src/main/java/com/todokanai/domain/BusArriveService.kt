package com.todokanai.domain

import com.todokanai.domain.stationarrrivetest.ServiceResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BusArriveService {

    @GET("getStationByUid?serviceKey=")
    fun getStationArrive(@Query("arsId") arsId: String ) : Call<ServiceResult>
}