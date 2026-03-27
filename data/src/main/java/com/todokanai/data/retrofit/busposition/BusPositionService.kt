package com.todokanai.data.retrofit.busposition

import com.todokanai.data.BuildConfig
import com.todokanai.data.retrofit.busposition.responsetype.busposbyrouteid.BusPosByRouteIdResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BusPositionService {

    @GET("getBusPosByRtidList?serviceKey=${BuildConfig.REST_API_KEY}")
    fun getBusPositions(@Query("routeId") routeId: String) : Call<BusPosByRouteIdResponse>
}