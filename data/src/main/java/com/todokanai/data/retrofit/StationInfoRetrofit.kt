package com.todokanai.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object StationInfoRetrofit {
    val BASE_URL = "http://ws.bus.go.kr/api/rest/stationinfo/"  // 서울특별시_정류소정보조회 서비스
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()
}