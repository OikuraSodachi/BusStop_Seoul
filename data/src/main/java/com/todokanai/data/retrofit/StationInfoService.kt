package com.todokanai.data.retrofit

import com.todokanai.data.BuildConfig
import com.todokanai.data.retrofit.stationbyname.StationByNameResponse
import com.todokanai.data.retrofit.stationbyposition.StationByPositionResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StationInfoService {

    @GET("getStationByName?serviceKey=${BuildConfig.REST_API_KEY}")
    fun getStationByName(@Query("stSrch") stSrch: String ) : Call<StationByNameResponse>

    @GET("getStationByPos?serviceKey=${BuildConfig.REST_API_KEY}")
    fun getStationByPosition(@Query("tmX") tmX: String, @Query("tmY") tmY: String, @Query("radius") radius: String) : Call<StationByPositionResponse>
}