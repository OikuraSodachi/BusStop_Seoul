package com.todokanai.domain

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object LineArriveRetrofit {
    val BASE_URL = "http://ws.bus.go.kr/api/rest/arrive/"
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()
}