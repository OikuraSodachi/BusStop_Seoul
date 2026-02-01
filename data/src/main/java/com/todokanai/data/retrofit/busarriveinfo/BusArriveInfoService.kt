package com.todokanai.data.retrofit.busarriveinfo

import com.todokanai.data.BuildConfig
import com.todokanai.data.retrofit.busarriveinfo.responsetype.arrinfobyrouteall.ArriveInfoByRouteAllResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BusArriveInfoService {

    @GET("getArrInfoByRouteAll?serviceKey=${BuildConfig.REST_API_KEY}")
    fun getArriveInfoByRouteAll(@Query("busRouteId") busRouteId: String): Call<ArriveInfoByRouteAllResponse>

}