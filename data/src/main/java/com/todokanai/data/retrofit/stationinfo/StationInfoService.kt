package com.todokanai.data.retrofit.stationinfo

import com.todokanai.data.BuildConfig
import com.todokanai.data.retrofit.stationinfo.responsetype.stationbyname.StationByNameResponse
import com.todokanai.data.retrofit.stationinfo.responsetype.stationbyposition.StationByPositionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StationInfoService {

    @GET("getStationByName?serviceKey=${BuildConfig.REST_API_KEY}")
    fun getStationByName(@Query("stSrch") stSrch: String ) : Call<StationByNameResponse>

    @GET("getStationByPos?serviceKey=${BuildConfig.REST_API_KEY}")
    fun getStationByPosition(@Query("tmX") tmX: String, @Query("tmY") tmY: String, @Query("radius") radius: String) : Call<StationByPositionResponse>
}