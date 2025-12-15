package com.todokanai.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object BusArriveRetrofit {

    private val BASE_URL = "http://ws.bus.go.kr/api/rest/arrive/"   //서울특별시_버스도착정보조회 서비스
    val busArriveRetrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()
}