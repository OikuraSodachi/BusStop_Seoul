package com.todokanai.data.retrofit.busarriveinfo

import com.todokanai.data.Constants.BUS_ARRIVE_INFO_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object BusArriveInfoRetrofit {
    val busArriveInfoRetrofit = Retrofit.Builder()
        .baseUrl(BUS_ARRIVE_INFO_BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

}