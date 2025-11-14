package com.todokanai.domain

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BusArriveRetrofit {
    val BASE_URL = "http://ws.bus.go.kr/api/rest/stationinfo/getStationByName"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}