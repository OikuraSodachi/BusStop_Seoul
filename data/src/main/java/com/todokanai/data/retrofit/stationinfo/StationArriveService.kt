package com.todokanai.data.retrofit.stationinfo

import com.todokanai.data.BuildConfig
import com.todokanai.data.retrofit.stationinfo.responsetype.stationarrive.StationArriveResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StationArriveService {

    @GET("getStationByUid?serviceKey=${BuildConfig.REST_API_KEY}")
    fun getStationArrive(@Query("arsId") arsId: String ) : Call<StationArriveResponse>
}