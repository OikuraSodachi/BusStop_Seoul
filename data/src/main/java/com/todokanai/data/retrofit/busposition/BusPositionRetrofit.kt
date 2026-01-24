package com.todokanai.data.retrofit.busposition

import com.todokanai.data.Constants.BUS_POSITION_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object BusPositionRetrofit {
    val busPositionRetrofit = Retrofit.Builder()
        .baseUrl(BUS_POSITION_BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()
}