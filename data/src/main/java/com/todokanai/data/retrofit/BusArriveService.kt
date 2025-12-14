package com.todokanai.data.retrofit

import com.todokanai.data.BuildConfig
import com.todokanai.data.retrofit.stationarrive.ServiceResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BusArriveService {

    @GET("getStationByUid?serviceKey=${BuildConfig.REST_API_KEY}")
    fun getStationArrive(@Query("arsId") arsId: String ) : Call<ServiceResult>
}