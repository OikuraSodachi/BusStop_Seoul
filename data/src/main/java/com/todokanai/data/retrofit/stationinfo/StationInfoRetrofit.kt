package com.todokanai.data.retrofit.stationinfo

import com.todokanai.data.Constants.STATION_INFO_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object StationInfoRetrofit {
    val stationInfoRetrofit = Retrofit.Builder()
        .baseUrl(STATION_INFO_BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()
}